package com.example.eventBus.controller;

import com.example.eventBus.command.CreateOrderCommand;
import com.example.eventBus.model.Order;
import com.example.eventBus.service.OrderService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/orders")
public class Orders {
    private  final OrderService orderService;

    public Orders(OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> listOrders = orderService.getAllOrders();
         return ResponseEntity.status(HttpStatus.OK).body(listOrders);
     }


    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderCommand command) {
        Order createdOrder = orderService.createOrder(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
