package com.mirotic91.demo.member.application;

import com.mirotic91.demo.common.model.Email;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberRepository;
import com.mirotic91.demo.member.domain.exception.EmailDuplicateException;
import com.mirotic91.demo.member.ui.dto.MemberSignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSignUpService {

    private final MemberRepository memberRepository;

    public Member doSignUp(final MemberSignUp dto) {
        checkEmailDuplication(dto.getEmail());
        Member member = dto.toEntity();
        return memberRepository.save(member);
    }

    private void checkEmailDuplication(Email email) {
        boolean exists =  memberRepository.existsByEmail(email);
        if (exists) {
            throw new EmailDuplicateException();
        }
    }

}
