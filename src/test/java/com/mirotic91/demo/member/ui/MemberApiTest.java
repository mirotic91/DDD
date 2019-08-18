package com.mirotic91.demo.member.ui;

import com.mirotic91.demo.common.IntegrationTest;
import com.mirotic91.demo.member.application.MemberHelperService;
import com.mirotic91.demo.member.application.MemberSignUpService;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberBuilder;
import com.mirotic91.demo.member.ui.dto.MemberSignUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberApiTest extends IntegrationTest {

    @Autowired
    MemberSignUpService memberSignUpService;

    @Autowired
    MemberHelperService memberHelperService;

    @Test
    @DisplayName("회원 가입")
    void signUp() throws Exception {
        Member member = MemberBuilder.build();
        MemberSignUp dto = MemberSignUpBuilder.create(member);

        ResultActions resultActions = requestSignUp(dto);

        resultActions.andExpect(status().isCreated());
    }

    private ResultActions requestSignUp(MemberSignUp dto) throws Exception {
        return mvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)));
    }

    @Test
    @DisplayName("회원 목록 조회")
    void findAll() throws Exception {
        ResultActions resultActions = requestFindAll();

        resultActions.andExpect(status().isOk());
    }

    private ResultActions requestFindAll() throws Exception {
        return mvc.perform(get("/api/members"));
    }

}