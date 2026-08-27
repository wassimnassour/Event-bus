package com.example.eventBus.command;

public record CreateOrderCommand(String item, int quantity) {}