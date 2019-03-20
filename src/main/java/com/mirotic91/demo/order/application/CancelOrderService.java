package com.mirotic91.demo.order.application;

import com.mirotic91.demo.order.domain.Order;
import com.mirotic91.demo.order.domain.OrderRepository;
import com.mirotic91.demo.order.domain.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CancelOrderService {

    private final OrderRepository orderRepository;

    public void cancel(Long id) {
        Order order = findByIdElseThrow(id);
        order.cancel();
    }

    private Order findByIdElseThrow(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElseThrow(OrderNotFoundException::new);
    }

}
