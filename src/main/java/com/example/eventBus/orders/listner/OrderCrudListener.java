package com.example.eventBus.orders.listner;

import com.example.eventBus.orders.event.OrderCancledEvent;
import com.example.eventBus.orders.event.OrderCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderCrudListener {
    public void createOrder(OrderCreatedEvent event) {
        System.out.println("Event is created " + event);
    }

    public void cancelOrder(OrderCancledEvent event) {
        System.out.println("Event is Canceled " + event);
    }

}
