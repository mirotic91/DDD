package com.mirotic91.demo.member.domain.exception;

import com.mirotic91.demo.common.exception.ErrorCode;
import com.mirotic91.demo.common.exception.InvalidValueException;

public class PasswordNotMatchException extends InvalidValueException {

  public PasswordNotMatchException() {
    super(ErrorCode.PASSWORD_NOT_MATCHED);
  }

}
