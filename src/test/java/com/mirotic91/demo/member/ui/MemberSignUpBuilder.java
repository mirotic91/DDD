package com.mirotic91.demo.member.ui;

import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.ui.dto.MemberSignUp;

public class MemberSignUpBuilder {

    public static MemberSignUp create(Member member) {
        return MemberSignUp.builder()
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword().getValue())
                .build();
    }

}