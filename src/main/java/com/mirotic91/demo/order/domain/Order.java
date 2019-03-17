package com.mirotic91.demo.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Table(name = "tb_order")
@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private OrderState state;

    @Embedded
    private Orderer orderer;

    @Embedded
    private ShippingInfo shippingInfo;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "total_amounts"))
    private Money totalAmounts;

    @Transient
    private List<OrderLine> orderLines;

    @Builder
    public Order(Orderer orderer, ShippingInfo shippingInfo, List<OrderLine> orderLines) {
        this.state = OrderState.PAYMENT_WAITING;
        setOrderer(orderer);
        setShippingInfo(shippingInfo);
        setOrderLines(orderLines);
    }

    private void setOrderer(Orderer orderer) {
        if (orderer == null) {
            throw new IllegalArgumentException("no Orderer");
        }
        this.orderer = orderer;
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null) {
            throw new IllegalArgumentException("no ShippingInfo");
        }
        this.shippingInfo = shippingInfo;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (CollectionUtils.isEmpty(orderLines)) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = Money.from(orderLines.stream().mapToInt(ol -> ol.getAmounts().getValue()).sum());
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        if (!state.isShippingChangeable()) {
            throw new IllegalStateException(String.format("can't change order shipping in state[%s]", state));
        }

        this.shippingInfo = newShippingInfo;
    }

    public void changeShipped() {
        if (state != OrderState.PREPARING) {
            throw new IllegalStateException(String.format("can't change orderState shipped in state[%s]", state));
        }
        this.state = OrderState.SHIPPED;
    }

    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    private void verifyNotYetShipped() {
        if (!state.isShippingChangeable()) {
            throw new IllegalStateException("already shipped");
        }
    }

}
