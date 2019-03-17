package com.mirotic91.demo.order.domain;

public class OrderLineBuilder {

    public static OrderLine build() {
        return OrderLine.builder()
                .productId(1L)
                .price(Money.from(500))
                .quantity(3)
                .build();
    }

}