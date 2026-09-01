package com.example.eventBus.clients.service;

import com.example.eventBus.clients.command.CreateClientCommand;
import com.example.eventBus.clients.model.Client;
import com.example.eventBus.clients.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll().stream().toList();
    }

    @Override
    public Client createClient(CreateClientCommand clientCommand) {
        Client client = new Client();
        client.setName(clientCommand.name());
        client.setEmail(clientCommand.email());
        clientRepository.save(client);

        return client;
    }
}
