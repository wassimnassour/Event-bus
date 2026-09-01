package com.example.eventBus.config;

import com.example.eventBus.eventBus.AsyncEventBus;
import com.example.eventBus.eventBus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class EventBusConfig {
    @Bean
    public EventBus eventBus() {
        int NUMBER_OF_THREADS = 4;
        return new AsyncEventBus(Executors.newFixedThreadPool(NUMBER_OF_THREADS), NUMBER_OF_THREADS);
    }
}
