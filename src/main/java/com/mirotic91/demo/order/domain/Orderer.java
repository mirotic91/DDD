package com.mirotic91.demo.order.domain;

import com.mirotic91.demo.common.model.Email;
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
public class Orderer {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first", column = @Column(name = "orderer_first_name")),
            @AttributeOverride(name = "last", column = @Column(name = "orderer_last_name"))
    })
    private Name name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "orderer_email"))
    private Email email;

    @Builder
    public Orderer(Name name, Email email) {
        this.name = name;
        this.email = email;
    }
}
