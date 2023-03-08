package com.academy.exceptions;

public class ValidationErrorException extends Exception{
    public ValidationErrorException() {
    }

    public ValidationErrorException(String message) {
        super(message);
    }

    public ValidationErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
