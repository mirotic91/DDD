package com.mirotic91.demo.member.domain;

import com.mirotic91.demo.common.model.Name;
import com.mirotic91.demo.member.domain.exception.PasswordNotMatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    private static Member member;

    @BeforeAll
    static void setUp() {
        Name name = Name.builder()
            .first("jonguk")
            .last("park")
            .build();

        Password password = Password.of("old");

        member = Member.builder()
            .name(name)
            .password(password)
            .build();
    }

    @Test
    @DisplayName("비밀번호 변경")
    void changePassword() {
        String newPassword = "new";
        member.changePassword("old", newPassword);
        Assertions.assertEquals(member.getPassword().getValue(), newPassword);
    }

    @Test
    @DisplayName("비밀번호 불일치로 변경 실패")
    void changePassword_passwordNotMatch() {
        String newPassword = "new";
        Assertions.assertThrows(PasswordNotMatchException.class, () -> member.changePassword("test", newPassword));
    }

}