package com.mirotic91.demo.member.application;

import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberRepository;
import com.mirotic91.demo.member.domain.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberHelperService {

    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        return optionalMember.orElseThrow(MemberNotFoundException::new);
    }

    public void deleteById(Long id) {
        Member member = findById(id);
        memberRepository.delete(member);
    }
}
