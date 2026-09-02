package com.example.eventBus.orders.listener;

import com.example.eventBus.orders.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderAuditListener {
    private static final Logger log = LoggerFactory.getLogger(OrderAuditListener.class);

    public void onAnyActionOnOrder(OrderEvent event) {
        log.info("Order audit listener triggered for event: {}", event);
    }
}
