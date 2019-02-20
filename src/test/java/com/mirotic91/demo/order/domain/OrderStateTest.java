package com.mirotic91.demo.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderStateTest {

    @Test
    @DisplayName("배송정보 변경 가능여부 확인")
    void isShippingChangeable() {
        assertFalse(OrderState.SHIPPED.isShippingChangeable());
        assertTrue(OrderState.PREPARING.isShippingChangeable());
    }
}