package com.mirotic91.demo.order.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderLineTest {

    @Test
    void calculateAmounts() {
        OrderLine orderLine = OrderLineBuilder.build();
        assertThat(orderLine.getAmounts().getValue()).isEqualTo(1500);
    }
}