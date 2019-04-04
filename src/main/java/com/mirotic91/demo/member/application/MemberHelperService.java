package com.mirotic91.demo.member.application;

import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberRepository;
import com.mirotic91.demo.member.domain.exception.MemberNotFoundException;
import com.mirotic91.demo.member.ui.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberHelperService {

    private final MemberRepository memberRepository;

    public List<MemberResponse> findAll() {
        List<Member> members = memberRepository.findByOrderByCreateAtDesc();
        return members.stream().map(MemberResponse::new).collect(Collectors.toList());
    }

    public Member findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        return optionalMember.orElseThrow(MemberNotFoundException::new);
    }

    public void deleteById(Long id) {
        Member member = findById(id);
        memberRepository.delete(member);
    }
}
