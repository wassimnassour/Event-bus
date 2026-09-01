package com.example.eventBus.config;

@FunctionalInterface
public interface Subscription {
    void unSubscribe();
}
