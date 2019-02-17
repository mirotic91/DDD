package com.mirotic91.demo.order.domain;

import lombok.Getter;

@Getter
public class Order {

    private OrderState state;

    private ShippingInfo shippingInfo;
}
