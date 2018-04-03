package com.example.virtualstore.infrastructure.deserializer;

import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import com.example.virtualstore.infrastructure.wrapper.ProductWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ProductHolderDeserializer extends JsonDeserializer<ProductHolderWrapper> {
    @Override
    public ProductHolderWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        ProductDeserializer productDeserializer = new ProductDeserializer();

        JsonNode productWrapperNode = jsonNode.get("product");
        ProductWrapper productWrapper = productDeserializer.deserialize(productWrapperNode);
        Product product = productWrapper.getProduct();

        int quantity = jsonNode.get("quantity").asInt();

        ProductHolder productHolder = new ProductHolder(product, quantity);

        return new ProductHolderWrapper(productHolder);
    }
}
