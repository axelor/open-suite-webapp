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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReleaseProcessor {

  public Release process(Collection<ChangelogEntry> changelogEntries, String version) {

    Objects.requireNonNull(version);
    Objects.requireNonNull(changelogEntries);

    validate(changelogEntries);

    Release release = new Release();
    release.setVersion(version);
    release.setDate(getCurrentDate());

    Map<ModuleName, List<ChangelogEntry>> entriesGroupedByModules =
        changelogEntries.stream().collect(Collectors.groupingBy(ChangelogEntry::getModule));
    release.setEntries(entriesGroupedByModules);

    return release;
  }

  private static String getCurrentDate() {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return today.format(formatter);
  }

  private void validate(Collection<ChangelogEntry> changelogEntries) {
    Objects.requireNonNull(changelogEntries);

    Optional<ChangelogEntry> entryWithNullTitle =
        changelogEntries.stream().filter(entry -> entry.getTitle() == null).findFirst();
    if (entryWithNullTitle.isPresent()) {
      throw new IllegalArgumentException(
          "Title cannot be null in changelog entry: " + entryWithNullTitle.get());
    }

    Optional<ChangelogEntry> entryWithoutModule =
        changelogEntries.stream().filter(entry -> entry.getModule() == null).findFirst();
    if (entryWithoutModule.isPresent()) {
      throw new IllegalArgumentException(
          "Module cannot be null in changelog entry: " + entryWithoutModule.get());
    }

    Optional<ChangelogEntry> entryWithNonExistingModule =
        changelogEntries.stream()
            .filter(entry -> ModuleName.UNKNOWN.equals(entry.getModule()))
            .findFirst();
    if (entryWithNonExistingModule.isPresent()) {
      throw new IllegalArgumentException(
          "The module filled in changelog entry is not recognized: "
              + entryWithNonExistingModule.get());
    }
  }
}
