package com.mirotic91.demo.order.domain;

import lombok.Getter;

@Getter
public class Product {

    private String name;

    private Money price;

    private String detail;
}
