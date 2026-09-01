package com.example.eventBus.orders.event;

import com.example.eventBus.config.Event;

import java.time.Instant;
import java.util.Objects;

public record OrderCancledEvent(String orderId, Instant occurredAt) implements Event {
    public OrderCancledEvent{
        Objects.requireNonNull(orderId , "Order id is required");
    }

    public static OrderCancledEvent now(String orderId ){
          return new OrderCancledEvent(orderId , Instant.now());
    }
}
