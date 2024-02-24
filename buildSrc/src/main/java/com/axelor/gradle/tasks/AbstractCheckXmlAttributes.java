package com.axelor.gradle.tasks;

import com.axelor.gradle.tasks.xmlcheck.XmlLineException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.FileTree;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.SkipWhenEmpty;
import org.gradle.api.tasks.TaskAction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public abstract class AbstractCheckXmlAttributes extends DefaultTask {

  protected Validator validator;

  @InputFiles @SkipWhenEmpty protected FileTree files;

  protected List<String> errorList = new ArrayList<>();

  public FileTree getFiles() {
    return files;
  }

  public void setFiles(FileTree files) {
    this.files = files;
  }

  @TaskAction
  public void check() throws XmlLineException {
    boolean initialized = false;
    for (File file : getFiles()) {
      if (!initialized) {
        initialized = initialize(file, findSchemaPath());
      }
      checkXmlFile(file);
    }
    if (!errorList.isEmpty()) {
      throw new XmlLineException(String.join("\n", errorList));
    }
  }

  protected boolean initialize(File inputFile, String schemaUrl) throws XmlLineException {
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document document = dBuilder.parse(inputFile);
      document.getDocumentElement().normalize();
      Element root = document.getDocumentElement();
      String schemaLocation = root.getAttribute("xsi:schemaLocation");
      String[] urls = schemaLocation.split("\\s+");

      if (urls.length == 2 && urls[0].equals(schemaUrl)) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new URL(urls[1]));
        this.validator = schema.newValidator();
        return true;
      }
      return false;
    } catch (Exception e) {
      throw new XmlLineException(e);
    }
  }

  protected abstract String findSchemaPath();

  protected void checkXmlFile(File file) throws XmlLineException {
    try {
      validator.validate(new StreamSource(file));
    } catch (SAXException e) {
      addErrorsProperlyToErrorList(errorList, e);
    } catch (IOException e) {
      throw new XmlLineException(e);
    }
  }

  protected void addErrorsProperlyToErrorList(List<String> errorList, Exception error) {
    if (!error.getLocalizedMessage().startsWith("cvc-elt.1.a")) {
      errorList.addAll(List.of(error.toString().split(";")));
    }
  }
}
