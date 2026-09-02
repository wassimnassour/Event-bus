package com.example.eventBus.clients.service;

import com.example.eventBus.clients.command.CreateClientCommand;
import com.example.eventBus.clients.event.ClientCreatedEvent;
import com.example.eventBus.clients.model.Client;
import com.example.eventBus.clients.repository.ClientRepository;
import com.example.eventBus.eventBus.EventBus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final EventBus eventBus;

    public ClientServiceImpl(ClientRepository clientRepository, EventBus eventBus) {
        this.clientRepository = clientRepository;
        this.eventBus = eventBus;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(CreateClientCommand clientCommand) {
        Client client = new Client();
        client.setName(clientCommand.name());
        client.setEmail(clientCommand.email());
        clientRepository.save(client);

        eventBus.publish(ClientCreatedEvent.now(client.getId(), client.getName(), client.getEmail()));

        return client;
    }
}

