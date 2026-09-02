package com.example.eventBus.orders.event;

import com.example.eventBus.config.Event;


public sealed interface OrderEvent extends Event
        permits OrderCreatedEvent, OrderCanceledEvent {
}