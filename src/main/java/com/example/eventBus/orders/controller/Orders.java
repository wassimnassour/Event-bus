package com.example.eventBus.orders.controller;

import com.example.eventBus.orders.command.CreateOrderCommand;
import com.example.eventBus.orders.command.OrderCanceledCommand;
import com.example.eventBus.orders.model.Order;
import com.example.eventBus.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class Orders {
    private final OrderService orderService;

    public Orders(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> listOrders = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(listOrders);
    }

    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderCommand command) {
        Order createdOrder = orderService.createOrder(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
    @PostMapping("/cancel")
    public ResponseEntity<?> CancelOrder(@RequestParam OrderCanceledCommand orderId){
          orderService.cancelOrder(orderId);
          return ResponseEntity.noContent().build();
    }
}
