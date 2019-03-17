package com.mirotic91.demo.order.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    private static Order order;

    @BeforeAll
    static void setUp() {
        order = OrderBuilder.build();
    }

    @Test
    void build() {
        assertThat(order.getOrderer().getEmail().getValue()).isEqualTo("jonguk1221@gmail.com");
        assertThat(order.getOrderer().getName().getFullName()).isEqualTo("jonguk park");
        assertThat(order.getShippingInfo().getReceiver().getName().getFullName()).isEqualTo("jonguk park");
        assertThat(order.getShippingInfo().getReceiver().getPhone()).isEqualTo("01033338888");
        assertThat(order.getShippingInfo().getAddress().getZipCode()).isEqualTo("13579");
        assertThat(order.getShippingInfo().getAddress().getPrimary()).isEqualTo("seoul apt");
        assertThat(order.getShippingInfo().getAddress().getDetail()).isEqualTo("101-1001");
        assertThat(order.getShippingInfo().getMessage()).isEqualTo("plz");
        assertThat(order.getTotalAmounts().getValue()).isEqualTo(1500);
    }

}