package com.mirotic91.demo.member.domain;

import com.mirotic91.demo.common.model.Name;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tb_member")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @EmbeddedId
    private MemberId id;

    @Embedded
    private Name name;

    @Embedded
    private Password password;

}
