package com.example.virtualstore.domain.repository;

import com.example.virtualstore.domain.entity.Client;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientRepository {

    private List<Client> clients;

    public ClientRepository() {
        clients = new LinkedList<>();
    }

    public int save(Client client) {
        this.clients.add(client);
        return client.getId();
    }

    public int getNextIdToUse() {
        return clients.size()+1;
    }

    public List<Client> findAll() {
        return Collections.unmodifiableList(clients);
    }

    public Client findById(int id) {
        return clients.stream().filter(client -> client.getId() == id).findFirst().orElse(new Client(0, ""));
    }

    public List<Client> findByName(String name) {
        return Collections.unmodifiableList(clients.stream().filter(client -> client.getName().contains(name)).collect(Collectors.toList()));
    }

}
