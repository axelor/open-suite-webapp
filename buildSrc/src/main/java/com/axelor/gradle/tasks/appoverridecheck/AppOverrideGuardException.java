package com.axelor.gradle.tasks.appoverridecheck;

public class AppOverrideGuardException extends Exception {
  public AppOverrideGuardException(Throwable throwable) {
    super(throwable);
  }

  public AppOverrideGuardException(String message) {
    super(message);
  }
}
