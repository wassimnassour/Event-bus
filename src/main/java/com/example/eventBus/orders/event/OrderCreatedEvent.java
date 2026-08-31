package com.example.eventBus.orders.event;

import java.time.Instant;

public record OrderCreatedEvent(Long orderId, String item, int quantity, Instant createdAt) {}