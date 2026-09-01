package com.example.eventBus.config;

@FunctionalInterface
public interface Consumer<T> {
   void accept(T t);
}
