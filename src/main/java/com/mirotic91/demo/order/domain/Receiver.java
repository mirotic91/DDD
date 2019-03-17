package com.mirotic91.demo.order.domain;

import com.mirotic91.demo.common.model.Name;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Receiver {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first", column = @Column(name = "receiver_first_name")),
            @AttributeOverride(name = "last", column = @Column(name = "receiver_last_name"))
    })
    private Name name;

    @Column(name = "receiver_phone")
    private String phone;

    @Builder
    public Receiver(Name name, final String phone) {
        this.name = name;
        this.phone = phone;
    }
}
