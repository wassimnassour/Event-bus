package com.example.eventBus.config;


import java.time.Instant;

public interface Event {
    Instant occurredAt();
}