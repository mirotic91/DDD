package com.mirotic91.demo.member.application;

import com.mirotic91.demo.common.MockTest;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberBuilder;
import com.mirotic91.demo.member.domain.Password;
import com.mirotic91.demo.member.domain.PasswordBuilder;
import com.mirotic91.demo.member.domain.exception.PasswordNotMatchException;
import com.mirotic91.demo.member.ui.MemberPasswordUpdateBuilder;
import com.mirotic91.demo.member.ui.dto.MemberPasswordUpdate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class MemberPasswordServiceTest extends MockTest {

    @InjectMocks
    private MemberPasswordService memberPasswordService;

    @Mock
    private MemberHelperService memberHelperService;

    private Member member;

    @BeforeEach
    void setUp() {
        member = MemberBuilder.build();
    }

    @Test
    @DisplayName("비밀번호 변경")
    void change() {
        given(memberHelperService.findById(anyLong())).willReturn(member);
        Password password = PasswordBuilder.build();
        MemberPasswordUpdate memberPasswordUpdate = MemberPasswordUpdateBuilder.create(password);

        final Member updateMember = memberPasswordService.change(anyLong(), memberPasswordUpdate);

        assertThat(updateMember.getPassword().getValue()).isNotEqualTo(memberPasswordUpdate.getPassword().getValue());
        assertThat(updateMember.getPassword().getValue()).isEqualTo(memberPasswordUpdate.getPassword().getNewValue());
    }

    @Test
    @DisplayName("비밀번호 불일치로 변경 실패")
    void change_passwordNotMatch() {
        given(memberHelperService.findById(anyLong())).willReturn(member);
        Password password = PasswordBuilder.build("test", "fail");
        MemberPasswordUpdate memberPasswordUpdate = MemberPasswordUpdateBuilder.create(password);

        Assertions.assertThrows(PasswordNotMatchException.class, () -> memberPasswordService.change(anyLong(), memberPasswordUpdate));
    }

}