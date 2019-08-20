package com.mirotic91.demo.member.ui;

import com.mirotic91.demo.common.IntegrationTest;
import com.mirotic91.demo.common.model.Email;
import com.mirotic91.demo.common.model.Name;
import com.mirotic91.demo.member.application.MemberHelperService;
import com.mirotic91.demo.member.application.MemberSignUpService;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberBuilder;
import com.mirotic91.demo.member.domain.Password;
import com.mirotic91.demo.member.ui.dto.MemberSignUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        resultActions.andExpect(jsonPath("email.value").value(member.getEmail().getValue()));
        resultActions.andExpect(jsonPath("name.fullName").value(member.getName().getFullName()));
    }

    @Test
    @DisplayName("회원 가입 - 입력 정보 오류")
    void signUp_invalidEmail() throws Exception {
        Email email = Email.from("invalid.com");
        Name name = Name.builder().build();
        Password password = Password.from("secret");
        Member member = MemberBuilder.createMember(email, name, password);
        MemberSignUp dto = MemberSignUpBuilder.create(member);

        ResultActions resultActions = requestSignUp(dto);

        resultActions.andExpect(status().isBadRequest());
    }

    private ResultActions requestSignUp(MemberSignUp dto) throws Exception {
        return mvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)));
    }

    @Test
    @DisplayName("회원 조회")
    void find() throws Exception {
        Email email = Email.from("mirotic@google.com");
        Name name = Name.builder().first("jonguk").last("park").build();
        Password password = Password.from("secret");
        Member member = MemberBuilder.createMember(email, name, password);
        MemberSignUp dto = MemberSignUpBuilder.create(member);

        member = memberSignUpService.doSignUp(dto);
        ResultActions resultActions = requestFind(member.getId());

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("email.value").value(member.getEmail().getValue()));
        resultActions.andExpect(jsonPath("name.fullName").value(member.getName().getFullName()));
    }

    private ResultActions requestFind(Long id) throws Exception {
        return mvc.perform(get("/api/members/{id}", id));
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

    @Test
    @DisplayName("회원 삭제")
    void remove() throws Exception {
        Member member = MemberBuilder.build();
        MemberSignUp dto = MemberSignUpBuilder.create(member);

        member = memberSignUpService.doSignUp(dto);
        ResultActions resultActions = requestDelete(member.getId());

        resultActions.andExpect(status().isNoContent());
    }

    private ResultActions requestDelete(Long id) throws Exception {
        return mvc.perform(delete("/api/members/{id}", id));
    }

}