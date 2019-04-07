package com.mirotic91.demo.member.ui;

import com.mirotic91.demo.member.domain.Password;
import com.mirotic91.demo.member.ui.dto.MemberPasswordUpdate;

public class MemberPasswordUpdateBuilder {

    public static MemberPasswordUpdate create(Password password) {
        return MemberPasswordUpdate.builder()
                .password(password)
                .build();
    }

}