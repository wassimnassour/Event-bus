package com.example.eventBus.eventBus;

import com.example.eventBus.config.Consumer;
import com.example.eventBus.config.Event;
import com.example.eventBus.config.Subscription;
import com.example.eventBus.orders.event.OrderCreatedEvent;


public interface EventBus {

     <T extends Event> Subscription  subscribe(Class<T> eventType, Consumer<T> listener);


     void publish(Event event);

}