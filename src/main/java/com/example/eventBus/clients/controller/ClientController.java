package com.example.eventBus.clients.controller;

import com.example.eventBus.clients.command.CreateClientCommand;
import com.example.eventBus.clients.model.Client;
import com.example.eventBus.clients.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> listClients = clientService.getAllClients();
        return ResponseEntity.status(HttpStatus.OK).body(listClients);
    }

    @PostMapping("")
    public ResponseEntity<Client> createClient(@RequestBody CreateClientCommand command) {
        Client createdClient = clientService.createClient(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }
}
