package com.example.eventBus.orders.event;

import java.time.Instant;
import java.util.Objects;

public record OrderCanceledEvent(Long orderId, Instant occurredAt) implements OrderEvent {
    public OrderCanceledEvent {
        Objects.requireNonNull(orderId, "Order id is required");
        Objects.requireNonNull(occurredAt, "occurredAt must not be null");
    }

    public static OrderCanceledEvent now(Long orderId) {
        return new OrderCanceledEvent(orderId, Instant.now());
    }
}

