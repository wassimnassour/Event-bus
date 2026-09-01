package com.example.eventBus.orders.event;

import com.example.eventBus.config.Event;

import java.time.Instant;
import java.util.Objects;

public record OrderCreatedEvent(Long orderId, String item, int quantity,Instant occurredAt) implements Event {

    public OrderCreatedEvent {
        Objects.requireNonNull(item, "Item is Required");
        if(quantity<=0){
            throw  new IllegalArgumentException("Quantity must be required");
        }
        Objects.requireNonNull(occurredAt, "occurredAt must not be null");
    }

    @Override
    public Instant occurredAt() {
        return null;
    }

    public static OrderCreatedEvent now (Long orderId, String item, int quantity){
        return new OrderCreatedEvent(orderId , item, quantity , Instant.now());
    }
}