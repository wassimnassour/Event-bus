package com.example.eventBus.orders.command;

public record CreateOrderCommand(String item, int quantity) {}