package com.example.virtualstore.infrastructure.serializer;

import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.infrastructure.wrapper.ProductWrapper;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProductSerializer extends JsonSerializer<ProductWrapper> {

    @Override
    public void serialize(ProductWrapper productWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Product product = productWrapper.getProduct();
        jsonGenerator = serialize(productWrapper, jsonGenerator);
    }

@JsonUnwrapped
    public JsonGenerator serialize(ProductWrapper productWrapper, JsonGenerator jsonGenerator) throws IOException {
        Product product = productWrapper.getProduct();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("id", product.getId());
        jsonGenerator.writeObjectField("name", product.getName());
        jsonGenerator.writeObjectField("price", product.getPrice());
        jsonGenerator.writeEndObject();
        return jsonGenerator;
    }
}
