package com.example.virtualstore.infrastructure.wrapper;

import com.example.virtualstore.domain.entity.Client;
import com.example.virtualstore.infrastructure.deserializer.ClientDeserializer;
import com.example.virtualstore.infrastructure.serializer.ClientSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ClientSerializer.class)
@JsonDeserialize(using = ClientDeserializer.class)
public class ClientWrapper {

    private final Client client;


    public ClientWrapper(final Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

}
