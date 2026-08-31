package com.example.eventBus.clients.event;

import java.time.Instant;

public record ClientCreatedEvent(Long clientId, String name, String email, Instant createdAt) {}
