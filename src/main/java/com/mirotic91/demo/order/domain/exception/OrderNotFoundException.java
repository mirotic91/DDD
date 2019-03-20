package com.mirotic91.demo.order.domain.exception;


import com.mirotic91.demo.common.exception.EntityNotFoundException;
import com.mirotic91.demo.common.exception.ErrorCode;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND);
    }

}
