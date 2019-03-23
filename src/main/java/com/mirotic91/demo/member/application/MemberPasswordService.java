package com.mirotic91.demo.member.application;

import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberRepository;
import com.mirotic91.demo.member.domain.exception.MemberNotFoundException;
import com.mirotic91.demo.member.ui.dto.MemberPasswordUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberPasswordService {

    private final MemberRepository memberRepository;

    public Member change(final Long memberId, final MemberPasswordUpdate dto) {
        Member member = findByIdElseThrow(memberId);
        member.changePassword(dto.getPassword());
        return member;
    }

    private Member findByIdElseThrow(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        return optionalMember.orElseThrow(MemberNotFoundException::new);
    }

}
