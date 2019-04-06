package com.mirotic91.demo.member.application;

import com.mirotic91.demo.common.MockTest;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberBuilder;
import com.mirotic91.demo.member.domain.MemberRepository;
import com.mirotic91.demo.member.domain.exception.MemberNotFoundException;
import com.mirotic91.demo.member.ui.dto.MemberResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class MemberHelperServiceTest extends MockTest {

    @InjectMocks
    private MemberHelperService memberHelperService;

    @Mock
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        member = MemberBuilder.build();
    }

    @Test
    @DisplayName("회원 목록 조회")
    void findAll() {
        given(memberRepository.findByOrderByCreateAtDesc()).willReturn(Arrays.asList(member, member));

        List<MemberResponse> list = memberHelperService.findAll();

        assertThat(list).isNotEmpty().hasSize(2);
    }

    @Test
    @DisplayName("회원 조회")
    void findById() {
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));

        final Member findMember = memberHelperService.findById(anyLong());

        assertThat(findMember).isNotNull().isEqualTo(member);
    }

    @Test
    @DisplayName("존재하지 않는 회원 조회")
    void findById_notExists() {
        given(memberRepository.findById(anyLong())).willReturn(Optional.empty());

        Assertions.assertThrows(MemberNotFoundException.class, () -> memberHelperService.findById(anyLong()));
    }

    @Test
    @DisplayName("회원 삭제")
    void deleteById() {
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));

        memberHelperService.deleteById(anyLong());
    }

    @Test
    @DisplayName("존재하지 않는 회원 삭제")
    void deleteById_notExists() {
        given(memberRepository.findById(anyLong())).willReturn(Optional.empty());

        Assertions.assertThrows(MemberNotFoundException.class, () -> memberHelperService.deleteById(anyLong()));
    }
}