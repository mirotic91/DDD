package com.mirotic91.demo.member.domain;

import com.mirotic91.demo.common.model.Name;
import com.mirotic91.demo.member.domain.exception.PasswordNotMatchException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_member")
@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Password password;

    @Builder
    public Member(final Name name, final Password password) {
        this.name = name;
        this.password = password;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!password.matches(oldPassword)) {
            throw new PasswordNotMatchException();
        }
        this.password = Password.of(newPassword);
    }

}
