package com.axelor.gradle.tasks;

import com.axelor.gradle.tasks.xmlcheck.XmlLineException;
import java.io.File;
import org.gradle.api.tasks.TaskAction;

public class CheckXmlImportsAttributes extends AbstractCheckXmlAttributes {

  protected static final String SCHEMA_PATH = "http://axelor.com/xml/ns/data-import";

  @Override
  protected String findSchemaPath() {
    return SCHEMA_PATH;
  }
}
