package com.mirotic91.demo.member.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Password {

    @Column(name = "password")
    private String value;

    Password(String value) {
        this.value = value;
    }

    public boolean matches(String password) {
        return this.value.equals(password);
    }

}