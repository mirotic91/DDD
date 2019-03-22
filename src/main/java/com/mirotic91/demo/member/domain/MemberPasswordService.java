package com.mirotic91.demo.member.domain;

import com.mirotic91.demo.member.domain.dto.MemberPasswordUpdate;
import com.mirotic91.demo.member.domain.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberPasswordService {

    private final MemberRepository memberRepository;

    public Member changePassword(final Long memberId, final MemberPasswordUpdate dto) {
        Member member = findByIdElseThrow(memberId);
        member.changePassword(dto.getPassword());
        return member;
    }

    private Member findByIdElseThrow(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        return optionalMember.orElseThrow(MemberNotFoundException::new);
    }

}
