/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2005-2020 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.gradle.tasks.changelog;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class ReleaseGenerator {

  private static final String NEW_LINE = System.lineSeparator();

  public String generate(Release release) {
    StringBuilder releaseContent = new StringBuilder();

    appendHeader(releaseContent, release);
    appendEntries(releaseContent, release);
    appendDeveloperEntries(releaseContent, release);

    return releaseContent.toString();
  }

  private void appendEntries(StringBuilder content, Release release) {
    if (release.getEntries() == null) {
      return;
    }

    for (ModuleName moduleName : ModuleName.values()) {
      if (release.getEntries().containsKey(moduleName)) {
        appendEntriesPerModule(content, moduleName, release.getEntries().get(moduleName));
      }
    }
  }

  private void appendEntriesPerModule(
      StringBuilder content, ModuleName moduleName, List<ChangelogEntry> entries) {
    if (entries == null || entries.isEmpty()) {
      return;
    }
    content.append("#### ").append(moduleName.getTitle()).append(NEW_LINE).append(NEW_LINE);
    for (ChangelogEntry entry : entries) {
      content.append(MessageFormat.format("* {0}", entry.getTitle())).append(NEW_LINE);
    }
    content.append(NEW_LINE);
  }

  private void appendDeveloperEntries(StringBuilder content, Release release) {
    if (release.getEntries() == null) {
      return;
    }
    if (release.getEntries().values().stream()
        .flatMap(Collection::stream)
        .anyMatch(this::hasDeveloperComment)) {
      content.append(NEW_LINE).append("### Developer").append(NEW_LINE).append(NEW_LINE);
    }
    for (ModuleName moduleName : ModuleName.values()) {
      if (release.getEntries().containsKey(moduleName)) {
        List<ChangelogEntry> changelogDevEntryList =
            release.getEntries().get(moduleName).stream()
                .filter(this::hasDeveloperComment)
                .collect(Collectors.toList());
        appendDevEntriesPerModule(content, moduleName, changelogDevEntryList);
      }
    }
  }

  private boolean hasDeveloperComment(ChangelogEntry entry) {
    return entry.getDeveloper() != null && !entry.getDeveloper().isEmpty();
  }

  private void appendDevEntriesPerModule(
      StringBuilder content, ModuleName moduleName, List<ChangelogEntry> entries) {
    if (entries == null || entries.isEmpty()) {
      return;
    }
    content.append("#### ").append(moduleName.getTitle()).append(NEW_LINE).append(NEW_LINE);

    String devEntrySeparator = NEW_LINE + NEW_LINE + "---" + NEW_LINE + NEW_LINE;
    content.append(
        entries.stream()
            .map(this::entryToFormattedDeveloperString)
            .collect(Collectors.joining(devEntrySeparator)));
    content.append(NEW_LINE);
    content.append(NEW_LINE);
  }

  private String entryToFormattedDeveloperString(ChangelogEntry entry) {
    List<String> lines =
        new ArrayList<>(
            Arrays.asList(StringUtils.strip(entry.getDeveloper().trim(), "\"").split("\n")));
    return String.join(NEW_LINE, lines);
  }

  private void appendHeader(StringBuilder content, Release release) {
    content
        .append(MessageFormat.format("## [{0}] ({1})", release.getVersion(), release.getDate()))
        .append(NEW_LINE)
        .append(NEW_LINE)
        .append("### Fixes")
        .append(NEW_LINE);
  }
}
