package com.axelor.gradle.tasks;

import com.axelor.gradle.tasks.xmlcheck.XmlLineException;
import java.io.File;
import org.gradle.api.tasks.TaskAction;

public class CheckXmlViewsAttributes extends AbstractCheckXmlAttributes {

  protected static final String SCHEMA_PATH = "http://axelor.com/xml/ns/object-views";

  @TaskAction
  public void check() throws XmlLineException {
    boolean initialized = false;
    for (File file : getFiles()) {
      if (!initialized) {
        initialized = initialize(file, SCHEMA_PATH);
      }
      checkXmlFile(file);
    }
    if (!errorList.isEmpty()) {
      throw new XmlLineException(String.join("\n", errorList));
    }
  }
}
