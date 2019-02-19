package com.mirotic91.demo.order.domain;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Getter
public class Order {

    private Orderer orderer;

    private OrderState state;

    private ShippingInfo shippingInfo;

    private Money totalAmounts;

    private List<OrderLine> orderLines;

    public Order(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderer(orderer);
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    private void setOrderer(Orderer orderer) {
        if (orderer == null) {
            throw new IllegalArgumentException("no Orderer");
        }
        this.orderer = orderer;
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

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null) {
            throw new IllegalArgumentException("no ShippingInfo");
        }
        this.shippingInfo = shippingInfo;
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

    private void calculateTotalAmounts() {
        this.totalAmounts = new Money(orderLines.stream().mapToInt(ol -> ol.getAmounts().getValue()).sum());
    }
}
