package com.example.eventBus.config;

import com.example.eventBus.eventBus.AsyncEventBus;
import com.example.eventBus.eventBus.EventBus;
import com.example.eventBus.orders.event.OrderCreatedEvent;
import com.example.eventBus.orders.listner.OrderCrudListener;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class EventBusConfig {
    private final EventBus eventBus;
    private final OrderCrudListener orderCrudListener;

    public EventBusConfig(EventBus eventBus, OrderCrudListener orderCrudListener) {
        this.orderCrudListener = orderCrudListener;
        this.eventBus = eventBus;
    }

    @Bean
    public EventBus eventBus() {
        return new AsyncEventBus(Executors.newFixedThreadPool(4));
    }

    @PostConstruct
    public void registerSubscriptions() {
        eventBus.subscribe(OrderCreatedEvent.class, orderCrudListener::createOrder);
    }
}
