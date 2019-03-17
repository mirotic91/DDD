package com.mirotic91.demo.common.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NameTest {

    @Test
    void getFullName() {
        Name name = NameBuilder.build();
        assertThat(name.getFullName()).isEqualTo("jonguk park");
    }
}