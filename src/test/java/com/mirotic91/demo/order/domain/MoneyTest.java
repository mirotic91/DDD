package com.mirotic91.demo.order.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    public void test() {
        Money money = Money.from(300);
        Assertions.assertThat(money.getValue()).isEqualTo(300);
        Assertions.assertThat(money.multiply(3).getValue()).isEqualTo(900);
    }

}