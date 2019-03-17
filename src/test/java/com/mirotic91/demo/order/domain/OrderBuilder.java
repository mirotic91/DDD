package com.mirotic91.demo.order.domain;

import com.mirotic91.demo.common.model.Address;
import com.mirotic91.demo.common.model.Email;
import com.mirotic91.demo.common.model.NameBuilder;

import java.util.Arrays;
import java.util.List;

public class OrderBuilder {

    public static Order build() {
        final Orderer orderer = buildOrderer();
        final ShippingInfo shippingInfo = buildShippingInfo();
        final OrderLine orderLine = OrderLineBuilder.build();
        List<OrderLine> orderLines = Arrays.asList(orderLine);
        return createOrder(orderer, shippingInfo, orderLines);
    }

    private static Orderer buildOrderer() {
        return Orderer.builder()
                .name(NameBuilder.build())
                .email(Email.from("jonguk1221@gmail.com"))
                .build();
    }

    private static ShippingInfo buildShippingInfo() {
        return ShippingInfo.builder()
                .receiver(buildReceiver())
                .address(buildAddress())
                .message("plz")
                .build();
    }

    private static Receiver buildReceiver() {
        return Receiver.builder()
                .name(NameBuilder.build())
                .phone("01033338888")
                .build();
    }

    private static Address buildAddress() {
        return Address.builder()
                .zipCode("13579")
                .primary("seoul apt")
                .detail("101-1001")
                .build();
    }

    private static Order createOrder(Orderer orderer, ShippingInfo shippingInfo, List<OrderLine> orderLines) {
        return Order.builder()
                .orderer(orderer)
                .shippingInfo(shippingInfo)
                .orderLines(orderLines)
                .build();
    }


}