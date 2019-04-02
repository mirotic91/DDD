package com.mirotic91.demo.member.application;

import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.ui.dto.MemberPasswordUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberPasswordService {

    private final MemberHelperService memberHelperService;

    public Member change(final Long memberId, final MemberPasswordUpdate dto) {
        Member member = memberHelperService.findById(memberId);
        member.changePassword(dto.getPassword());
        return member;
    }

}
