package com.mirotic91.demo.member.ui;

import com.mirotic91.demo.common.MockApiTest;
import com.mirotic91.demo.common.model.Email;
import com.mirotic91.demo.common.model.Name;
import com.mirotic91.demo.member.application.MemberHelperService;
import com.mirotic91.demo.member.application.MemberPasswordService;
import com.mirotic91.demo.member.application.MemberSignUpService;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.domain.MemberBuilder;
import com.mirotic91.demo.member.domain.Password;
import com.mirotic91.demo.member.ui.dto.MemberResponse;
import com.mirotic91.demo.member.ui.dto.MemberSignUp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class MemberMockApiTest extends MockApiTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MemberSignUpService memberSignUpService;

    @MockBean
    private MemberHelperService memberHelperService;

    @MockBean
    private MemberPasswordService memberPasswordService;

    private Member member;

    @BeforeEach
    void setUp() {
        mvc = buildMockMvc(context);
        member = MemberBuilder.build();
    }

    @Test
    @DisplayName("회원 가입")
    void signUp() throws Exception {
        MemberSignUp dto = MemberSignUpBuilder.create(member);
        given(memberSignUpService.doSignUp(any())).willReturn(member);

        ResultActions resultActions = requestSignUp(dto);

        resultActions.andExpect(status().isCreated());
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
        given(memberHelperService.findById(anyLong())).willReturn(member);

        ResultActions resultActions = requestFind(0L);

        resultActions.andExpect(status().isOk());
    }

    private ResultActions requestFind(Long id) throws Exception {
        return mvc.perform(get("/api/members/{id}", id));
    }

    @Test
    @DisplayName("회원 목록 조회")
    void findAll() throws Exception {
        MemberResponse memberResponse = new MemberResponse(member);
        given(memberHelperService.findAll()).willReturn(Arrays.asList(memberResponse));

        ResultActions resultActions = requestFindAll();

        resultActions.andExpect(status().isOk());
    }

    private ResultActions requestFindAll() throws Exception {
        return mvc.perform(get("/api/members"));
    }

    @Test
    @DisplayName("회원 삭제")
    void remove() throws Exception {
        given(memberHelperService.findById(anyLong())).willReturn(member);

        ResultActions resultActions = requestDelete(0L);

        resultActions.andExpect(status().isNoContent());
    }

    private ResultActions requestDelete(Long id) throws Exception {
        return mvc.perform(delete("/api/members/{id}", id));
    }

}