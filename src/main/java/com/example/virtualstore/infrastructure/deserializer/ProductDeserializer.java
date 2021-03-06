package com.example.virtualstore.infrastructure.deserializer;

import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.infrastructure.wrapper.ProductWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ProductDeserializer extends JsonDeserializer<ProductWrapper> {
    @Override
    public ProductWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(jsonNode);
    }

    public ProductWrapper deserialize(JsonNode jsonNode) {
        int id = jsonNode.get("id").asInt();
        String name = jsonNode.get("name").asText();
        double price = jsonNode.get("price").asDouble();
        Product product = new Product(id, name, price);
        return new ProductWrapper(product);

    }


}
