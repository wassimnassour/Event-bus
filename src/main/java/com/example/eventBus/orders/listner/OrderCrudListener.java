package com.example.eventBus.orders.listner;

import com.example.eventBus.orders.event.OrderCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderCrudListener {
    public void createOrder(OrderCreatedEvent event) {

    }

}
