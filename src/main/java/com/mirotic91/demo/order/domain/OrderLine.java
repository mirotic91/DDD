package com.mirotic91.demo.order.domain;

import lombok.Getter;

@Getter
public class OrderLine {

    private Product product;

    private Money price;

    private int quantity;

    private Money amounts;

    public OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = Money.from(price.getValue());
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    public Money calculateAmounts() {
        return price.multiply(quantity);
    }

}
