package com.example.virtualstore.application.service;

import com.example.virtualstore.domain.entity.Client;
import com.example.virtualstore.domain.entity.ShoppingCart;
import com.example.virtualstore.domain.repository.ClientRepository;
import com.example.virtualstore.infrastructure.wrapper.ClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public ClientService(ClientRepository clientRepository, ShoppingCartService shoppingCartService) {
        this.clientRepository = clientRepository;
        this.shoppingCartService = shoppingCartService;
        setDefaultClients();
    }

    public List<ClientWrapper> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientWrapper> clientWrappers = new LinkedList<>();
        for (Client client : clients) {
            ClientWrapper clientWrapper = new ClientWrapper(client);
            clientWrappers.add(clientWrapper);
        }
        return clientWrappers;
    }

    public ClientWrapper getClientWrapperById(int id) {
        return new ClientWrapper(clientRepository.findById(id));
    }

    public List<ClientWrapper> getClientsWithName(String name) {
        List<Client> clients = clientRepository.findByName(name);
        List<ClientWrapper> clientWrappers = new LinkedList<>();
        for (Client client : clients) {
            ClientWrapper clientWrapper = new ClientWrapper(client);
            clientWrappers.add(clientWrapper);
        }
        return clientWrappers;
    }

    public int addClient(String name) {

        int id = clientRepository.getNextIdToUse();
        Client client = new Client(id, name);
        shoppingCartService.addNewShoppingCart(id);
        return clientRepository.save(client);
    }

    private void setDefaultClients() {
        addClient("Ana");
        addClient("Bia");
        addClient("Claudia");
    }

}
