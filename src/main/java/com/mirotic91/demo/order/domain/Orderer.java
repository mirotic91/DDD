package com.mirotic91.demo.order.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Orderer {

    @Column(name = "orderer_name")
    private String name;

    @Column(name = "orderer_email")
    private String email;
}
