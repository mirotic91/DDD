package com.mirotic91.demo.member.domain;

import com.mirotic91.demo.member.domain.exception.PasswordNotMatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    private static Member member;

    private static Password password;

    @BeforeAll
    static void setUp() {
        member = MemberBuilder.build();
        password = PasswordBuilder.build();
    }

    @Test
    @DisplayName("비밀번호 변경")
    void changePassword() {
        member.changePassword(password);
        Assertions.assertEquals(member.getPassword().getValue(), password.getNewValue());
    }

    @Test
    @DisplayName("비밀번호 불일치로 변경 실패")
    void changePassword_passwordNotMatch() {
        Assertions.assertThrows(PasswordNotMatchException.class, () -> member.changePassword(password));
    }

}