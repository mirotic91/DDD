package com.mirotic91.demo.order.domain;

import com.mirotic91.demo.common.model.Email;
import com.mirotic91.demo.common.model.NameBuilder;

import java.util.Arrays;
import java.util.List;

public class OrderBuilder {

    public static Order build() {
        return build(OrderState.PAYMENT_WAITING);
    }

    public static Order build(OrderState state) {
        final Orderer orderer = buildOrderer();
        final ShippingInfo shippingInfo = ShippingInfoBuilder.build();
        final OrderLine orderLine = OrderLineBuilder.build();
        List<OrderLine> orderLines = Arrays.asList(orderLine);
        return createOrder(state, orderer, shippingInfo, orderLines);
    }

    private static Orderer buildOrderer() {
        return Orderer.builder()
                .name(NameBuilder.build())
                .email(Email.from("jonguk1221@gmail.com"))
                .build();
    }

    private static Order createOrder(OrderState state, Orderer orderer, ShippingInfo shippingInfo, List<OrderLine> orderLines) {
        return Order.builder()
                .state(state)
                .orderer(orderer)
                .shippingInfo(shippingInfo)
                .orderLines(orderLines)
                .build();
    }


}