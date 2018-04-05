package com.example.virtualstore.infrastructure.serializer;

import com.example.virtualstore.domain.entity.Client;
import com.example.virtualstore.domain.entity.Order;
import com.example.virtualstore.infrastructure.wrapper.ClientWrapper;
import com.example.virtualstore.infrastructure.wrapper.OrderWrapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class ClientSerializer extends JsonSerializer<ClientWrapper> {
    @Override
    public void serialize(ClientWrapper clientWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Client client = clientWrapper.getClient();
        jsonGenerator = serialize(client, jsonGenerator);
    }

    public JsonGenerator serialize(Client client, JsonGenerator jsonGenerator) throws IOException {
        List<Order> orders = client.getOrders();
        OrderSerializer orderSerializer = new OrderSerializer();

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("id", client.getId());
        jsonGenerator.writeObjectField("name", client.getName());
        jsonGenerator.writeFieldName("orders");
        jsonGenerator.writeStartArray();
        for (Order order : orders) {
            OrderWrapper orderWrapper = new OrderWrapper(order);
            jsonGenerator = orderSerializer.serialize(orderWrapper, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
        return jsonGenerator;
    }
}
