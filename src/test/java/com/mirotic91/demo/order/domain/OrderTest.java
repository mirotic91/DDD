package com.mirotic91.demo.order.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
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

    @Test
    @DisplayName("주문 출고 처리")
    void changeShipped() {
        order = OrderBuilder.build(OrderState.PREPARING);
        order.changeShipped();
        assertThat(order.getState()).isEqualTo(OrderState.SHIPPED);
    }

    @Test
    @DisplayName("결제 대기중 출고 처리 불가")
    void changeShipped_paymentWaitingState() {
        assertThat(order.getState()).isEqualTo(OrderState.PAYMENT_WAITING);
        assertThrows(IllegalStateException.class, () -> order.changeShipped());
    }

    @Test
    @DisplayName("배송 처리전 배송 메세지 변경")
    void changeShippingInfo() {
        String oldMessage = order.getShippingInfo().getMessage();
        String newMessage = "8282";

        order.changeShippingInfo(ShippingInfoBuilder.build(newMessage));
        assertThat(order.getShippingInfo().getMessage()).isNotEqualTo(oldMessage);
        assertThat(order.getShippingInfo().getMessage()).isEqualTo(newMessage);
    }

    @Test
    @DisplayName("배송 처리전 주문 취소")
    void cancel() {
        assertThat(order.getState()).isEqualTo(OrderState.PAYMENT_WAITING);
        order.cancel();
        assertThat(order.getState()).isEqualTo(OrderState.CANCELED);
    }

    @Test
    @DisplayName("배송중 주문 취소 불가")
    void cancel_deliveringState() {
        order = OrderBuilder.build(OrderState.DELIVERING);
        assertThrows(IllegalStateException.class, () -> order.cancel());
    }

}