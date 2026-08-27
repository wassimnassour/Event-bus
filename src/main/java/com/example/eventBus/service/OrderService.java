package com.example.eventBus.service;

import com.example.eventBus.command.CreateOrderCommand;
import com.example.eventBus.model.Order;
import com.example.eventBus.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private  final  OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository= orderRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll().stream().toList()  ;
    }
    public  Order createOrder(CreateOrderCommand orderCommand){
         Order order=  new Order();
         order.setQuantity(orderCommand.quantity());
         order.setItem(orderCommand.item());
         orderRepository.save(order);

         return order;
    }
}
