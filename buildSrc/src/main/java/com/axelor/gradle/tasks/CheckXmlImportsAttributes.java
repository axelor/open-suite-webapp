package com.axelor.gradle.tasks;

public class CheckXmlImportsAttributes extends AbstractCheckXmlAttributes {

  protected static final String SCHEMA_PATH = "http://axelor.com/xml/ns/data-import";

  @Override
  protected String findSchemaPath() {
    return SCHEMA_PATH;
  }
}
