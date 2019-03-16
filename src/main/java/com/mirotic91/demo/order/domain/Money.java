package com.mirotic91.demo.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {

    private int value;

    private Money(int value) {
        this.value = value;
    }

    public Money multiply(int multiplier) {
        return from(value * multiplier);
    }

    public static Money from(final int value) {
        return new Money(value);
    }
}
