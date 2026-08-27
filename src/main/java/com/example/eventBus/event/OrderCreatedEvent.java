package com.example.eventBus.event;

import java.time.Instant;

public record OrderCreatedEvent(Long orderId, String item, int quantity, Instant createdAt) {}