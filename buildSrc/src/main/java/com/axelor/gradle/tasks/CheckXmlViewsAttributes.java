package com.axelor.gradle.tasks;

import com.axelor.gradle.tasks.xmlcheck.XmlLineException;
import java.io.File;
import org.gradle.api.tasks.TaskAction;

public class CheckXmlViewsAttributes extends AbstractCheckXmlAttributes {

  protected static final String SCHEMA_PATH = "http://axelor.com/xml/ns/object-views";

  @Override
  protected String findSchemaPath() {
    return SCHEMA_PATH;
  }
}
