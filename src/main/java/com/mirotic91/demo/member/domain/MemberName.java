package com.mirotic91.demo.member.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class MemberName {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

}