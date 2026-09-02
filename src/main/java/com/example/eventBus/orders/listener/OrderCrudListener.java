package com.example.eventBus.orders.listener;

import com.example.eventBus.orders.event.OrderCanceledEvent;
import com.example.eventBus.orders.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderCrudListener {
    private static final Logger log = LoggerFactory.getLogger(OrderCrudListener.class);

    public void createOrder(OrderCreatedEvent event) {
        log.info("Order created event received: {}", event);
    }

    public void cancelOrder(OrderCanceledEvent event) {
        log.info("Order canceled event received: {}", event);
    }
}
