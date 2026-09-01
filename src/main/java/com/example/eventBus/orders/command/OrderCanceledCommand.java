package com.example.eventBus.orders.command;

import java.util.Objects;

public record OrderCanceledCommand(String orderId) {
    public OrderCanceledCommand{
        Objects.requireNonNull(orderId , "Order id is Required");
    }
}
