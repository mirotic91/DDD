package com.mirotic91.demo.common.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NameTest {

    @Test
    void getFullName() {
        Name name = Name.builder()
            .first("jonguk")
            .last("park")
            .build();

        assertThat(name.getFullName()).isEqualTo("jonguk park");
    }
}