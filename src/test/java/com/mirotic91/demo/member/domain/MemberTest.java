package com.mirotic91.demo.member.domain;

import com.mirotic91.demo.member.domain.exception.PasswordNotMatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    private Member member;

    private Password password;

    @BeforeEach
    void setUp() {
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
        password = PasswordBuilder.build("new", "new");
        Assertions.assertThrows(PasswordNotMatchException.class, () -> member.changePassword(password));
    }

}