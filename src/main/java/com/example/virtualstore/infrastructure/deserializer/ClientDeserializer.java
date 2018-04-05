package com.example.virtualstore.infrastructure.deserializer;

import com.example.virtualstore.domain.entity.Client;
import com.example.virtualstore.domain.entity.Order;
import com.example.virtualstore.infrastructure.wrapper.ClientWrapper;
import com.example.virtualstore.infrastructure.wrapper.OrderWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ClientDeserializer extends JsonDeserializer<ClientWrapper> {
    @Override
    public ClientWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        int id = jsonNode.get("id").asInt();
        String name = jsonNode.get("name").asText();

        List<Order> orders = new LinkedList<>();
        JsonNode ordersNode = jsonNode.get("orders");
        OrderDeserializer orderDeserializer = new OrderDeserializer();
        for (JsonNode orderNode : ordersNode) {
            OrderWrapper orderWrapper = orderDeserializer.deserialize(orderNode);
            orders.add(orderWrapper.getOrder());
        }

        return new ClientWrapper(new Client(id, name, orders));
    }
}
