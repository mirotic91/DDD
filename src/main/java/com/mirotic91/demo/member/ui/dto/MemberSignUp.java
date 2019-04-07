package com.mirotic91.demo.member.ui.dto;

import com.mirotic91.demo.common.model.Email;
import com.mirotic91.demo.common.model.Name;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.Password;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignUp {

    private Email email;

    private Name name;

    private Password password;

    public Member toEntity() {
        return Member.builder()
            .email(this.email)
            .name(this.name)
            .password(this.password)
            .build();
    }

    @Builder
    public MemberSignUp(Email email, Name name, Password password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}