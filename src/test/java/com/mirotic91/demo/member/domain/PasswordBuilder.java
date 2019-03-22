package com.mirotic91.demo.member.domain;

public class PasswordBuilder {

    public static Password build() {
        return Password.builder()
            .value("old")
            .newValue("new")
            .build();
    }

}