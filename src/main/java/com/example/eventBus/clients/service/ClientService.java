package com.example.eventBus.clients.service;

import com.example.eventBus.clients.command.CreateClientCommand;
import com.example.eventBus.clients.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client createClient(CreateClientCommand clientCommand);
}
