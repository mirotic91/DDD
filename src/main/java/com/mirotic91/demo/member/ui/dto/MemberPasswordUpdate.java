package com.mirotic91.demo.member.ui.dto;

import com.mirotic91.demo.member.domain.Password;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberPasswordUpdate {

    private Password password;

    @Builder
    public MemberPasswordUpdate(Password password) {
        this.password = password;
    }
}