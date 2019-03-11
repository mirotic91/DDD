package com.mirotic91.demo.exception;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidValueException(String message, ErrorCode errorCode) {
      super(message, errorCode);
    }
}