package com.example.virtualstore.ui.controller;

import com.example.virtualstore.application.service.ClientService;
import com.example.virtualstore.infrastructure.wrapper.ClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public List<ClientWrapper> getAllClients() {
        return clientService.getAllClients();
    }

    @RequestMapping(value = "/clients/withName", method = RequestMethod.GET)
    public List<ClientWrapper> getClientsWithName(@RequestParam String name) {
        return clientService.getClientsWithName(name);
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public ClientWrapper getClient(@PathVariable int id) {
        return clientService.getClientWrapperById(id);
    }

    @RequestMapping(value = "/clients/add", method = RequestMethod.POST)
    public String addClient(@RequestParam String name) {
        int id = clientService.addClient(name);
        return String.format("Client \"%s\" with id \"%d\" was added successfully!", name, id);
    }

}
