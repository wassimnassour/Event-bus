package com.example.eventBus.orders.service;

import com.example.eventBus.eventBus.EventBus;
import com.example.eventBus.orders.command.CreateOrderCommand;
import com.example.eventBus.orders.command.OrderCanceledCommand;
import com.example.eventBus.orders.event.OrderCanceledEvent;
import com.example.eventBus.orders.event.OrderCreatedEvent;
import com.example.eventBus.orders.model.Order;
import com.example.eventBus.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final EventBus eventBus;

    private final OrderRepository orderRepository;


    public OrderServiceImpl(EventBus eventBus, OrderRepository orderRepository) {
        this.eventBus = eventBus;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(CreateOrderCommand orderCommand) {
        Order order = new Order();
        order.setQuantity(orderCommand.quantity());
        order.setItem(orderCommand.item());
        orderRepository.save(order);

        eventBus.publish(OrderCreatedEvent.now(order.getId(), order.getItem(), order.getQuantity()));
        return order;
    }

    @Override
    public void cancelOrder(OrderCanceledCommand orderCanceledInput) {
        eventBus.publish(OrderCanceledEvent.now(orderCanceledInput.orderId()));
    }
}
