package com.mirotic91.demo.order.domain;

public enum OrderState {

    PAYMENT_WAITING, PREPARING, SHIPPED, DELIVERING, DELIVERY_COMPLETED, CANCELED;

    public boolean isShippingChangeable() {
        return this == PAYMENT_WAITING || this == PREPARING;
    }
}
