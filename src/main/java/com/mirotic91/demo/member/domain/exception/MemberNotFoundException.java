package com.mirotic91.demo.member.domain.exception;


import com.mirotic91.demo.common.exception.EntityNotFoundException;
import com.mirotic91.demo.common.exception.ErrorCode;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }

}
