package com.mirotic91.demo;

import com.mirotic91.demo.common.model.Email;
import com.mirotic91.demo.common.model.Name;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberRepository;
import com.mirotic91.demo.member.domain.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class SampleDataCommandLineRunner implements CommandLineRunner {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void run(String... args) {
        memberRepository.save(
                Member.builder()
                        .email(Email.from("sample@gmail.com"))
                        .name(Name.builder().first("reus").last("marco").build())
                        .password(Password.from("champion"))
                        .build());
    }

}