package com.axelor.gradle.tasks;

import com.axelor.gradle.tasks.javacheck.JavaInjectAnnotationException;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.FileTree;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.SkipWhenEmpty;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CheckJavaInjectAnnotationImport extends DefaultTask {
    @InputFiles
    @SkipWhenEmpty
    private FileTree files;

    public static final String GOOGLE_JAVA_IMPORT = "import com.google.inject.Inject;";
    public static final String IMPORT_ERROR = ": this java file uses the Inject annotation from Google, please use the jakarta one.";

    private List<String> errorList = new ArrayList<>();

    public FileTree getFiles() {
        return files;
    }

    public void setFiles(FileTree files) {
        this.files = files;
    }

    @TaskAction
    public void check() throws JavaInjectAnnotationException {
        for (File file : getFiles()) {
            checkInjectAnnotationImport(file);
        }
        if (!errorList.isEmpty()) {
            throw new JavaInjectAnnotationException(String.join("", errorList));
        }
    }

    protected void checkInjectAnnotationImport(File file) throws JavaInjectAnnotationException {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            boolean hasInjectImport = lines.stream()
                    .anyMatch(line -> line.trim().equals(GOOGLE_JAVA_IMPORT));
            if(hasInjectImport) {
                errorList.add(file.getName() + IMPORT_ERROR + "\n");
            }
        }catch (Exception e){
            throw new JavaInjectAnnotationException(e);
        }
    }
}
