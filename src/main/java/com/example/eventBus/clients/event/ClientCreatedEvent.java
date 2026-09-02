package com.example.eventBus.clients.event;

import com.example.eventBus.config.Event;

import java.time.Instant;
import java.util.Objects;

public record ClientCreatedEvent(Long clientId, String name, String email, Instant occurredAt) implements Event {
    public ClientCreatedEvent {
        Objects.requireNonNull(name, "Name is required");
        Objects.requireNonNull(email, "Email is required");
        Objects.requireNonNull(occurredAt, "occurredAt must not be null");
    }

    public static ClientCreatedEvent now(Long clientId, String name, String email) {
        return new ClientCreatedEvent(clientId, name, email, Instant.now());
    }
}

