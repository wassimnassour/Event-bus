package com.example.eventBus.eventBus;

import com.example.eventBus.config.Consumer;
import com.example.eventBus.config.Event;
import com.example.eventBus.config.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class AsyncEventBus implements EventBus, AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(AsyncEventBus.class);

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
    public <T extends Event> Subscription subscribe(Class<T> eventType, Consumer<T> listener) {
        List<Consumer<?>> listeners = subscriptions.computeIfAbsent(
                eventType, key -> new CopyOnWriteArrayList<>());
        listeners.add(listener);
        return () -> listeners.remove(listener);
    }

    @Override
    public void publish(Event event) {
        pendingEvents.add(event);
    }

    @SuppressWarnings("unchecked")
    public void deliver(Event event) {
        for (Map.Entry<Class<?>, List<Consumer<?>>> entry : subscriptions.entrySet()) {
            Class<?> subscribedType = entry.getKey();
            if (subscribedType.isInstance(event)) {
                List<Consumer<?>> listeners = entry.getValue();

                if (listeners == null || listeners.isEmpty()) {
                    continue;
                }

                for (Consumer<?> listener : listeners) {
                    try {
                        ((Consumer<Event>) listener).accept(event);
                    } catch (Throwable t) {
                        log.error("Error dispatching event {} to listener {}: {}",
                                event.getClass().getSimpleName(), listener.getClass().getName(), t.getMessage(), t);
                    }
                }
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
            } catch (Throwable t) {
                log.error("Unexpected error in event bus consume loop", t);
            }
        }
    }

    @Override
    public void close() {
        shutdown();
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
