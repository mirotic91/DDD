package com.mirotic91.demo.member.domain;

import com.mirotic91.demo.common.model.Email;
import com.mirotic91.demo.common.model.Name;
import com.mirotic91.demo.common.model.NameBuilder;

public class MemberBuilder {

    public static Member build() {
        final Name name = NameBuilder.build();
        final Email email = Email.from("jonguk1221@gmail.com");
        final Password password = Password.from("old");
        return createMember(email, name, password);
    }

    private static Member createMember(Email email, Name name, Password password) {
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }


}