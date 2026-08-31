package com.example.eventBus.orders.repository;

import com.example.eventBus.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}