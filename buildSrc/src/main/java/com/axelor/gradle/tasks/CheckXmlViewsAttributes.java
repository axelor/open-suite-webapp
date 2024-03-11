package com.axelor.gradle.tasks;

public class CheckXmlViewsAttributes extends AbstractCheckXmlAttributes {

  protected static final String SCHEMA_PATH = "http://axelor.com/xml/ns/object-views";

  @Override
  protected String findSchemaPath() {
    return SCHEMA_PATH;
  }
}
