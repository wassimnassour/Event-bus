package com.example.eventBus.clients.repository;

import com.example.eventBus.clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}
