package com.mirotic91.demo.order.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Access(AccessType.FIELD)
@EqualsAndHashCode(of = "number")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderNo implements Serializable {

    @Column(name = "order_number")
    private String number;

}
