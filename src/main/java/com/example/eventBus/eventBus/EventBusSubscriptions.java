package com.example.eventBus.eventBus;

import com.example.eventBus.orders.event.OrderCancledEvent;
import com.example.eventBus.orders.event.OrderCreatedEvent;
import com.example.eventBus.orders.listner.OrderCrudListener;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBusSubscriptions {
    private final EventBus eventBus;
    private final OrderCrudListener orderCrudListener;

    public EventBusSubscriptions(EventBus eventBus, OrderCrudListener orderCrudListener) {
        this.orderCrudListener = orderCrudListener;
        this.eventBus = eventBus;
    }
    @PostConstruct
    public void registerSubscriptions() {
        eventBus.subscribe(OrderCreatedEvent.class, orderCrudListener::createOrder);
        eventBus.subscribe(OrderCancledEvent.class ,orderCrudListener::cancelOrder );
    }
}
