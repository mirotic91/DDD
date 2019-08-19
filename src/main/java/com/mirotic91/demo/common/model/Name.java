package com.mirotic91.demo.common.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {

    @NotBlank
    @Column(name = "first_name")
    private String first;

    @NotBlank
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