package com.mirotic91.demo.member.application;

import com.mirotic91.demo.common.MockTest;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberBuilder;
import com.mirotic91.demo.member.domain.MemberRepository;
import com.mirotic91.demo.member.domain.exception.EmailDuplicateException;
import com.mirotic91.demo.member.ui.MemberSignUpBuilder;
import com.mirotic91.demo.member.ui.dto.MemberSignUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class MemberSignUpServiceTest extends MockTest {

    @InjectMocks
    private MemberSignUpService memberSignUpService;

    @Mock
    private MemberRepository memberRepository;

    private Member member;

    private MemberSignUp memberSignUp;

    @BeforeEach
    void setUp() {
        member = MemberBuilder.build();
        memberSignUp = MemberSignUpBuilder.create(member);
    }

    @Test
    @DisplayName("회원가입")
    void doSignUp() {
        given(memberRepository.existsByEmail(any())).willReturn(false);
        given(memberRepository.save(any())).willReturn(member);

        final Member saveMember = memberSignUpService.doSignUp(memberSignUp);

        assertThat(saveMember).isEqualTo(member);
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입")
    void doSignUp_emailDuplicate() {
        given(memberRepository.existsByEmail(any())).willReturn(true);

        Assertions.assertThrows(EmailDuplicateException.class, () -> memberSignUpService.doSignUp(memberSignUp));
    }

}