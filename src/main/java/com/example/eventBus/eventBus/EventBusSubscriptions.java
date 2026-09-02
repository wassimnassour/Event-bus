package com.example.eventBus.eventBus;

import com.example.eventBus.orders.event.OrderCanceledEvent;
import com.example.eventBus.orders.event.OrderCreatedEvent;
import com.example.eventBus.orders.event.OrderEvent;
import com.example.eventBus.orders.listener.OrderAuditListener;
import com.example.eventBus.orders.listener.OrderCrudListener;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBusSubscriptions {
    private final EventBus eventBus;
    private final OrderCrudListener orderCrudListener;
    private final OrderAuditListener orderAuditListener;

    public EventBusSubscriptions(EventBus eventBus, OrderCrudListener orderCrudListener, OrderAuditListener orderAuditListener) {
        this.orderCrudListener = orderCrudListener;
        this.eventBus = eventBus;
        this.orderAuditListener = orderAuditListener;
    }

    @PostConstruct
    public void registerSubscriptions() {
        eventBus.subscribe(OrderCreatedEvent.class, orderCrudListener::createOrder);
        eventBus.subscribe(OrderCanceledEvent.class, orderCrudListener::cancelOrder);

        // Test publish to all class that matches the parent class it's like  Fanout Exchange
        eventBus.subscribe(OrderEvent.class, orderAuditListener::onAnyActionOnOrder);
    }
}
