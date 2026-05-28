package com.axelor.gradle.tasks.javacheck;

public class JavaInjectAnnotationException extends Exception {
    public JavaInjectAnnotationException(Throwable throwable) {
        super(throwable);
    }

    public JavaInjectAnnotationException(String message) {
        super(message);
    }
}
