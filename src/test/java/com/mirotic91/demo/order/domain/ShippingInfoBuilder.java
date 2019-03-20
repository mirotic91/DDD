package com.mirotic91.demo.order.domain;

import com.mirotic91.demo.common.model.Address;
import com.mirotic91.demo.common.model.NameBuilder;

public class ShippingInfoBuilder {

    public static ShippingInfo build() {
        return build("plz");
    }

    public static ShippingInfo build(final String message) {
        return ShippingInfo.builder()
                .receiver(buildReceiver())
                .address(buildAddress())
                .message(message)
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

}
