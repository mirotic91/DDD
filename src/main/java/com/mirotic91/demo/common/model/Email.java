package com.mirotic91.demo.common.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @Column(name = "email", unique = true, nullable = false)
    private String value;

    private Email(final String value) {
        this.value = value;
    }

    public static Email from(final String value) {
        return new Email(value);
    }

}
