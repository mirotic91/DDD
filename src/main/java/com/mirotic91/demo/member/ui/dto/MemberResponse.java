package com.mirotic91.demo.member.ui.dto;

import com.mirotic91.demo.common.model.Email;
import com.mirotic91.demo.common.model.Name;
import com.mirotic91.demo.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private Email email;

    private Name name;

    public MemberResponse(final Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
    }
}