package com.mirotic91.demo.member.ui;

import com.mirotic91.demo.member.application.MemberPasswordService;
import com.mirotic91.demo.member.application.MemberSignUpService;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.ui.dto.MemberPasswordUpdate;
import com.mirotic91.demo.member.ui.dto.MemberResponse;
import com.mirotic91.demo.member.ui.dto.MemberSignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberSignUpService memberSignUpService;

    private final MemberPasswordService memberPasswordService;

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody MemberSignUp dto) {
        Member member = memberSignUpService.doSignUp(dto);
        return new ResponseEntity<>(new MemberResponse(member), HttpStatus.OK);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<MemberResponse> changePassword(@PathVariable Long id, @RequestBody MemberPasswordUpdate dto) {
        Member member = memberPasswordService.change(id, dto);
        return new ResponseEntity<>(new MemberResponse(member), HttpStatus.OK);
    }
}
