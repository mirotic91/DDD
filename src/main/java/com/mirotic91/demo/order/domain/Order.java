package com.mirotic91.demo.order.domain;

import lombok.Getter;

@Getter
public class Order {

    private OrderState state;

    private ShippingInfo shippingInfo;

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

}
