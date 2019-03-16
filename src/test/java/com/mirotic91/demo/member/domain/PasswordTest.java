package com.mirotic91.demo.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordTest {

    @Test
    @DisplayName("비밀번호 일치여부 확인")
    void matches() {
        Password password = Password.from("password");
        assertFalse(password.matches(""));
        assertFalse(password.matches(null));
        assertFalse(password.matches("word"));
        assertTrue(password.matches("password"));
    }
}