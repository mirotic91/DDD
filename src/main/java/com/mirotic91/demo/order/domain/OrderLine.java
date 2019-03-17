package com.mirotic91.demo.order.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderLine {

    private Long productId;

    private Money price;

    private int quantity;

    private Money amounts;

    @Builder
    public OrderLine(final Long productId, Money price, final int quantity) {
        this.productId = productId;
        this.price = Money.from(price.getValue());
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    public Money calculateAmounts() {
        return price.multiply(quantity);
    }

}
