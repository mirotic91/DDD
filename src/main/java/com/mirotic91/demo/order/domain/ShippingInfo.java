package com.mirotic91.demo.order.domain;

import lombok.Getter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
public class ShippingInfo {

    @Embedded
    private Receiver receiver;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "zipCode", column = @Column(name = "shipping_zip_code")),
        @AttributeOverride(name = "primary", column = @Column(name = "shipping_primary_address")),
        @AttributeOverride(name = "detail", column = @Column(name = "shipping_detail_address"))
    })
    private Address address;

    @Column(name = "shipping_message")
    private String message;
}
