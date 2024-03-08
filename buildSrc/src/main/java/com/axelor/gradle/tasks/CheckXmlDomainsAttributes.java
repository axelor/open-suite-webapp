package com.axelor.gradle.tasks;

public class CheckXmlDomainsAttributes extends AbstractCheckXmlAttributes {

  protected static final String SCHEMA_PATH = "http://axelor.com/xml/ns/domain-models";

  @Override
  protected String findSchemaPath() {
    return SCHEMA_PATH;
  }
}
