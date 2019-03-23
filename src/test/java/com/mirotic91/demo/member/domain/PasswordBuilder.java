package com.mirotic91.demo.member.domain;

public class PasswordBuilder {

    public static Password build() {
        return build("old", "new");
    }

    public static Password build(final String currentPassword, final String newPassword) {
        return Password.builder()
                .value(currentPassword)
                .newValue(newPassword)
                .build();
    }

}