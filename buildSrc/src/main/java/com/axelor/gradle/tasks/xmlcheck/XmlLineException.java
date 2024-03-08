package com.axelor.gradle.tasks.xmlcheck;

public class XmlLineException extends Exception {

  public XmlLineException(Throwable throwable) {
    super(throwable);
  }

  public XmlLineException(String message) {
    super(message);
  }
}
