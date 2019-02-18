package com.mirotic91.demo.order.domain;

import lombok.Getter;

@Getter
public class ShippingInfo {

    private Receiver receiver;

    private Address address;

    private String message;
}
