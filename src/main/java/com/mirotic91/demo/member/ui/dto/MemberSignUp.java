package com.mirotic91.demo.member.ui.dto;

import com.mirotic91.demo.common.model.Email;
import com.mirotic91.demo.common.model.Name;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.Password;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignUp {

    @Valid
    private Email email;

    @Valid
    private Name name;

    @NotBlank
    private String password;

    public Member toEntity() {
        return Member.builder()
            .email(this.email)
            .name(this.name)
            .password(Password.from(this.password))
            .build();
    }

    @Builder
    public MemberSignUp(Email email, Name name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}