package com.example.eventBus.orders.service;

import com.example.eventBus.orders.command.CreateOrderCommand;
import com.example.eventBus.orders.command.OrderCanceledCommand;
import com.example.eventBus.orders.model.Order;

import java.util.List;

public interface OrderService {
     List<Order> getAllOrders();
     Order  createOrder(CreateOrderCommand order);
     void cancelOrder(OrderCanceledCommand orderCanceledInput);
}
