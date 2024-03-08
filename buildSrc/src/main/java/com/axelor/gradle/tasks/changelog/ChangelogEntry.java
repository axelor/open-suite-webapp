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

public class ChangelogEntry {

  private String title;
  private ModuleName module;
  private String developer;

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setDeveloper(String developer) {
    this.developer = developer;
  }

  public String getDeveloper() {
    return developer;
  }

  public ModuleName getModule() {
    return module;
  }

  public void setModule(ModuleName module) {
    this.module = module;
  }

  @Override
  public String toString() {
    return "ChangelogEntry [module=" + getModule() + ", title=" + getTitle() + "]";
  }
}
