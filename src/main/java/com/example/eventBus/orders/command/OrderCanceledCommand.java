package com.example.eventBus.orders.command;

import java.util.Objects;

public record OrderCanceledCommand(Long orderId) {
    public OrderCanceledCommand {
        Objects.requireNonNull(orderId, "Order id is required");
    }
}

