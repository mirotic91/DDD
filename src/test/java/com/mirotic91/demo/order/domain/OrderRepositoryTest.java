package com.mirotic91.demo.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("주문 객체 생성 확인")
    void test() {
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).isEmpty();

        Order order = OrderBuilder.build();
        orderRepository.save(order);

        orderList = orderRepository.findAll();
        assertThat(orderList).isNotEmpty();
        assertThat(orderList.contains(order)).isTrue();
    }

}