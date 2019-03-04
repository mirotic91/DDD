package com.mirotic91.demo.order.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "primary_address")
    private String primary;

    @Column(name = "detail_address")
    private String detail;
}
