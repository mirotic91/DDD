package com.mirotic91.demo.member.domain;

import com.mirotic91.demo.common.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(Email email);

    List<Member> findByOrderByCreateAtDesc();

}
