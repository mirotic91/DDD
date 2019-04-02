package com.mirotic91.demo.member.domain.exception;

import com.mirotic91.demo.common.exception.ErrorCode;
import com.mirotic91.demo.common.exception.InvalidValueException;

public class EmailDuplicateException extends InvalidValueException {

  public EmailDuplicateException() {
    super(ErrorCode.EMAIL_DUPLICATED);
  }

}
