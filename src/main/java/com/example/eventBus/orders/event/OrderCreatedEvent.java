package com.example.eventBus.orders.event;

import java.time.Instant;
import java.util.Objects;

public record OrderCreatedEvent(Long orderId, String item, int quantity, Instant occurredAt) implements OrderEvent {

    public OrderCreatedEvent {
        Objects.requireNonNull(item, "Item is Required");
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be required");
        }
        Objects.requireNonNull(occurredAt, "occurredAt must not be null");
    }

    public static OrderCreatedEvent now(Long orderId, String item, int quantity) {
        return new OrderCreatedEvent(orderId, item, quantity, Instant.now());
    }
}