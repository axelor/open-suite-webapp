package com.axelor.gradle.tasks;

import com.axelor.gradle.tasks.xmlcheck.XmlLineException;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.FileTree;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.SkipWhenEmpty;
import org.gradle.api.tasks.TaskAction;
import org.xml.sax.SAXException;

public class CheckXmlAttributes extends DefaultTask {

  private static final String LOCAL_SCHEMA = "object-views.xsd";

  @InputFiles @SkipWhenEmpty private FileTree files;

  private List<String> errorList = new ArrayList<>();

  public FileTree getFiles() {
    return files;
  }

  public void setFiles(FileTree files) {
    this.files = files;
  }

  @TaskAction
  public void check() throws XmlLineException {
    for (File file : getFiles()) {
      checkXmlFile(file);
    }
    if (!errorList.isEmpty()) {
      throw new XmlLineException(String.join("\n", errorList));
    }
  }

  protected void checkXmlFile(File file) throws XmlLineException {
    try {
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = schemaFactory.newSchema(Resources.getResource(LOCAL_SCHEMA));
      Validator validator = schema.newValidator();

      validator.validate(new StreamSource(file));
    } catch (SAXException e) {
      addErrorsProperlyToErrorList(errorList, e.toString());
    } catch (IOException e) {
      throw new XmlLineException(e);
    }
  }

  protected void addErrorsProperlyToErrorList(List<String> errorList, String error) {
    errorList.addAll(List.of(error.split(";")));
  }
}
