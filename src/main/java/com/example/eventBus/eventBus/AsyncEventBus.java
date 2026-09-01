package com.example.eventBus.eventBus;

import com.example.eventBus.config.Consumer;
import com.example.eventBus.config.Event;
import com.example.eventBus.config.Subscription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class AsyncEventBus implements  EventBus {
     private  final ExecutorService executorService;

     private final Map<Class<?>,List<Consumer<?>>>  subscriptions =  new ConcurrentHashMap<>();

     public AsyncEventBus(ExecutorService executorService){
         this.executorService= executorService;
     }

    @Override
    public <T extends Event> Subscription subscribe(Class<T> eventType, Consumer<T> listner) {
         List<Consumer<?>> listeners =  subscriptions.computeIfAbsent(
                eventType , key-> new CopyOnWriteArrayList<>()
        );
        listeners.add(listner);
         return ()->listeners.remove(listner);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void publish(Event event) {
        List<Consumer<?>> listeners = subscriptions.get(event.getClass());

        if(listeners== null || listeners.isEmpty()){
            return;
        }
        for (Consumer<?> listener : listeners) {

            executorService.submit(() -> {
                try {
                    ((Consumer<Event>) listener).accept(event);
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }


    public void  shutdown(){
         executorService.shutdown();
         try{
             if(!executorService.awaitTermination(5 , TimeUnit.SECONDS)){
                 executorService.shutdownNow();
             }
         } catch (RuntimeException | InterruptedException e) {
             executorService.shutdownNow();
             Thread.currentThread().interrupt();
         }

    }
}
