package com.example.eventBus.eventBus;

import com.example.eventBus.config.Consumer;
import com.example.eventBus.config.Event;
import com.example.eventBus.config.Subscription;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class AsyncEventBus implements EventBus {
    private final ExecutorService executorService;

    private final Map<Class<?>, List<Consumer<?>>> subscriptions = new ConcurrentHashMap<>();
    private final BlockingQueue<Event> pendingEvents = new LinkedBlockingQueue<>();

    public AsyncEventBus(ExecutorService executorService, int threadsCount) {

        this.executorService = executorService;
        for (int i = 0; i < threadsCount; i++) {
            executorService.submit(this::consume);
        }

    }


    @Override
    public <T extends Event> Subscription subscribe(Class<T> eventType, Consumer<T> listner) {
        List<Consumer<?>> listeners = subscriptions.computeIfAbsent(
                eventType, key -> new CopyOnWriteArrayList<>()
        );
        listeners.add(listner);
        return () -> listeners.remove(listner);
    }

    @Override
    public void publish(Event event) {
        pendingEvents.add(event);
    }

    @SuppressWarnings("unchecked")
    public void deliver(Event event) {
        List<Consumer<?>> listeners = subscriptions.get(event.getClass());

        if (listeners == null || listeners.isEmpty()) {
            return;
        }

        for (Consumer<?> listener : listeners) {

            try {
                ((Consumer<Event>) listener).accept(event);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void consume() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Event event = pendingEvents.take();
                deliver(event);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }


    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (RuntimeException | InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }
}
