package com.example.virtualstore.infrastructure.serializer;

import com.example.virtualstore.domain.entity.Client;
import com.example.virtualstore.infrastructure.wrapper.ClientWrapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ClientSerializer extends JsonSerializer<ClientWrapper> {
    @Override
    public void serialize(ClientWrapper clientWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        /*
            private final int id;
    private final String name;
    private List<Order> orders;
         */



    }

    public JsonGenerator serialize(Client client, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("id", client.getId());
        jsonGenerator.writeObjectField("name", client.getName());
        jsonGenerator.writeFieldName("orders");
        jsonGenerator.writeEndObject();
        return jsonGenerator;
    }
}
