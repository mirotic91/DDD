package com.mirotic91.demo.member.domain;

import com.mirotic91.demo.common.model.Email;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = MemberBuilder.build();
        memberRepository.save(member);
    }

    @Test
    @DisplayName("존재하는 회원")
    void existsByEmail_true() {
        Email email = Email.from("jonguk1221@gmail.com");
        boolean existsByEmail = memberRepository.existsByEmail(email);
        Assertions.assertThat(existsByEmail).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 회원")
    void existsByEmail_false() {
        Email email = Email.from("test@gmail.com");
        boolean existsByEmail = memberRepository.existsByEmail(email);
        Assertions.assertThat(existsByEmail).isFalse();
    }

    @Test
    @DisplayName("생성/수정일시 확인")
    void checkSavedDateTime() {
        Member member = memberRepository.findAll().get(0);
        LocalDateTime createAt = member.getCreateAt();
        LocalDateTime updateAt = member.getUpdateAt();

        Password password = PasswordBuilder.build();
        member.changePassword(password);
        member = memberRepository.saveAndFlush(member);

        assertThat(createAt).isEqualTo(member.getCreateAt());
        assertThat(updateAt).isBefore(member.getUpdateAt());
    }

}