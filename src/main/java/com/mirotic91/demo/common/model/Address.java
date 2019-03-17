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
public class Address {

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "primary_address")
    private String primary;

    @Column(name = "detail_address")
    private String detail;

    @Builder
    public Address(final String zipCode, final String primary, final String detail) {
        this.zipCode = zipCode;
        this.primary = primary;
        this.detail = detail;
    }
}
