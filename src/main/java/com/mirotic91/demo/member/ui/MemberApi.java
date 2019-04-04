package com.mirotic91.demo.member.ui;

import com.mirotic91.demo.member.application.MemberHelperService;
import com.mirotic91.demo.member.application.MemberPasswordService;
import com.mirotic91.demo.member.application.MemberSignUpService;
import com.mirotic91.demo.member.domain.Member;
import com.mirotic91.demo.member.ui.dto.MemberPasswordUpdate;
import com.mirotic91.demo.member.ui.dto.MemberResponse;
import com.mirotic91.demo.member.ui.dto.MemberSignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberSignUpService memberSignUpService;

    private final MemberHelperService memberHelperService;

    private final MemberPasswordService memberPasswordService;

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody MemberSignUp dto) {
        Member member = memberSignUpService.doSignUp(dto);
        return new ResponseEntity<>(new MemberResponse(member), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> get(@PathVariable Long id) {
        Member member = memberHelperService.findById(id);
        return new ResponseEntity<>(new MemberResponse(member), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAll() {
        List<MemberResponse> members = memberHelperService.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberHelperService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<MemberResponse> changePassword(@PathVariable Long id, @RequestBody MemberPasswordUpdate dto) {
        Member member = memberPasswordService.change(id, dto);
        return new ResponseEntity<>(new MemberResponse(member), HttpStatus.OK);
    }
}
