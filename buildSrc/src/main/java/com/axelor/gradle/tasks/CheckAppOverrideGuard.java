package com.axelor.gradle.tasks;

import com.axelor.gradle.tasks.appoverridecheck.AppOverrideGuardException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.FileTree;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.SkipWhenEmpty;
import org.gradle.api.tasks.TaskAction;

/**
 * Checks that a Guice binding overriding a service of a lower module guards its behaviour on the
 * installation of its own app.
 *
 * <p>A binding declared in a {@code *Module.java} is active as soon as the module is deployed, no
 * matter whether the corresponding app is installed. An implementation overriding a class of
 * another module must therefore call {@code isApp("<app-code>")} and delegate to {@code super}
 * when the app is off, so that deploying a module never changes the behaviour of the modules it
 * sits on top of.
 *
 * <p>Classes listed in the allowlist file are skipped: they are either behaviour-neutral overrides,
 * overrides whose parent app is a hard prerequisite, or known debt still waiting to be swept.
 */
public class CheckAppOverrideGuard extends DefaultTask {

  @InputFiles @SkipWhenEmpty private FileTree files;

  @InputFiles @Optional private FileCollection allowlist;

  private static final Pattern BINDING_PATTERN =
      Pattern.compile("bind\\(\\s*([\\w.]+)\\.class\\s*\\)\\s*\\.\\s*to\\(\\s*([\\w.]+)\\.class\\s*\\)");

  private static final Pattern IMPORT_PATTERN = Pattern.compile("import\\s+([\\w.]+)\\s*;");

  private static final Pattern GUARD_PATTERN = Pattern.compile("\\bisApp\\s*\\(");

  private static final String SOURCE_ROOT = "/src/main/java/";

  private static final String ERROR =
      "%s overrides %s of module %s without any isApp(...) check.";

  private static final String HELP =
      "\nAn override must not change the behaviour of the overridden module when its own app is off."
          + "\nGuard the overriding methods on isApp(\"<app-code>\") and delegate to super, or add the"
          + "\nclass to the allowlist file with a justification if the override is legitimate.\n";

  /** Fully qualified name of every scanned class, indexed by simple name. */
  private final Map<String, List<String>> classesBySimpleName = new HashMap<>();

  /** Source file of every scanned class, indexed by fully qualified name. */
  private final Map<String, File> filesByClassName = new HashMap<>();

  private final List<String> errorList = new ArrayList<>();

  public FileTree getFiles() {
    return files;
  }

  public void setFiles(FileTree files) {
    this.files = files;
  }

  public FileCollection getAllowlist() {
    return allowlist;
  }

  public void setAllowlist(FileCollection allowlist) {
    this.allowlist = allowlist;
  }

  @TaskAction
  public void check() throws AppOverrideGuardException {
    try {
      indexClasses();
      Set<String> allowedClasses = readAllowlist();
      Map<String, List<String>> errorsByModule = new TreeMap<>();

      for (File file : getFiles()) {
        if (!file.getName().endsWith("Module.java")) {
          continue;
        }
        checkGuiceModule(file, allowedClasses, errorsByModule);
      }

      if (!errorsByModule.isEmpty()) {
        throw new AppOverrideGuardException(formatReport(errorsByModule));
      }
    } catch (AppOverrideGuardException e) {
      throw e;
    } catch (Exception e) {
      throw new AppOverrideGuardException(e);
    }
  }

  protected void indexClasses() throws Exception {
    for (File file : getFiles()) {
      String className = toClassName(file);
      if (className == null) {
        continue;
      }
      filesByClassName.put(className, file);
      classesBySimpleName
          .computeIfAbsent(simpleName(className), key -> new ArrayList<>())
          .add(className);
    }
  }

  protected void checkGuiceModule(
      File moduleFile, Set<String> allowedClasses, Map<String, List<String>> errorsByModule)
      throws Exception {

    String content = read(moduleFile);
    Map<String, String> imports = readImports(content);
    Matcher matcher = BINDING_PATTERN.matcher(content);

    while (matcher.find()) {
      String overridden = resolve(matcher.group(1), imports);
      String overriding = resolve(matcher.group(2), imports);
      if (overridden == null || overriding == null) {
        // A class we do not have the sources of, typically coming from the platform.
        continue;
      }

      String overriddenModule = moduleOf(filesByClassName.get(overridden));
      String overridingModule = moduleOf(filesByClassName.get(overriding));
      if (overriddenModule == null
          || overridingModule == null
          || overriddenModule.equals(overridingModule)) {
        // Binding internal to a module: it defines the module behaviour, it does not override it.
        continue;
      }

      if (allowedClasses.contains(overriding) || isGuarded(overriding)) {
        continue;
      }

      errorsByModule
          .computeIfAbsent(overridingModule, key -> new ArrayList<>())
          .add(String.format(ERROR, overriding, overridden, overriddenModule));
    }
  }

  protected boolean isGuarded(String className) throws Exception {
    return GUARD_PATTERN.matcher(read(filesByClassName.get(className))).find();
  }

  /**
   * Resolves the name used in a binding into a fully qualified name, using the imports of the Guice
   * module first, then the simple name when it is unambiguous. Returns null when the class is not
   * part of the scanned sources.
   */
  protected String resolve(String name, Map<String, String> imports) {
    String simpleName = simpleName(name);
    String imported = imports.get(simpleName);
    if (imported != null && filesByClassName.containsKey(imported)) {
      return imported;
    }
    List<String> candidates = classesBySimpleName.get(simpleName);
    return candidates != null && candidates.size() == 1 ? candidates.get(0) : null;
  }

  protected Map<String, String> readImports(String content) {
    Map<String, String> imports = new HashMap<>();
    Matcher matcher = IMPORT_PATTERN.matcher(content);
    while (matcher.find()) {
      String className = matcher.group(1);
      imports.put(simpleName(className), className);
    }
    return imports;
  }

  protected Set<String> readAllowlist() throws Exception {
    Set<String> allowedClasses = new HashSet<>();
    if (allowlist == null) {
      return allowedClasses;
    }
    for (File file : allowlist) {
      if (!file.exists()) {
        continue;
      }
      for (String line : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8)) {
        String entry = line.split("#", 2)[0].trim();
        if (!entry.isEmpty()) {
          allowedClasses.add(entry);
        }
      }
    }
    return allowedClasses;
  }

  /** Derives the fully qualified name of a class from the path of its source file. */
  protected String toClassName(File file) {
    String path = file.getPath().replace(File.separatorChar, '/');
    int index = path.indexOf(SOURCE_ROOT);
    if (index < 0 || !path.endsWith(".java")) {
      return null;
    }
    String relativePath = path.substring(index + SOURCE_ROOT.length(), path.length() - 5);
    return relativePath.replace('/', '.');
  }

  /** Derives the module of a class from the path of its source file. */
  protected String moduleOf(File file) {
    if (file == null) {
      return null;
    }
    String path = file.getPath().replace(File.separatorChar, '/');
    int index = path.indexOf(SOURCE_ROOT);
    if (index < 0) {
      return null;
    }
    String modulePath = path.substring(0, index);
    return modulePath.substring(modulePath.lastIndexOf('/') + 1);
  }

  protected String simpleName(String className) {
    return className.substring(className.lastIndexOf('.') + 1);
  }

  protected String read(File file) throws Exception {
    return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
  }

  protected String formatReport(Map<String, List<String>> errorsByModule) {
    StringBuilder report = new StringBuilder();
    int total = 0;
    Map<String, Integer> countByModule = new LinkedHashMap<>();

    for (Map.Entry<String, List<String>> entry : errorsByModule.entrySet()) {
      report.append("\n[").append(entry.getKey()).append("]\n");
      for (String error : entry.getValue()) {
        report.append("  ").append(error).append("\n");
      }
      countByModule.put(entry.getKey(), entry.getValue().size());
      total += entry.getValue().size();
    }

    report.append("\nUnguarded overrides: ").append(total).append("\n");
    for (Map.Entry<String, Integer> entry : countByModule.entrySet()) {
      report.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
    }
    return report.append(HELP).toString();
  }
}
