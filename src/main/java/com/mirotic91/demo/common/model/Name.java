package com.mirotic91.demo.common.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {

    @Column(name = "first_name")
    private String first;

    @Column(name = "last_name")
    private String last;

    @Builder
    public Name(final String first, final String last) {
        this.first = first;
        this.last = last;
    }

    public String getFullName() {
        return String.format("%s %s", this.first, this.last);
    }

}