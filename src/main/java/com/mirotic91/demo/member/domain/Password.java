package com.mirotic91.demo.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Column(name = "password", nullable = false)
    private String value;

    @Transient
    private String newValue;

    private Password(String value) {
        this.value = value;
    }

    public boolean matches(String password) {
        return this.value.equals(password);
    }

    public static Password from(final String value) {
        return new Password(value);
    }

    @Builder
    public Password(final String value, final String newValue) {
        this.value = value;
        this.newValue = newValue;
    }
}