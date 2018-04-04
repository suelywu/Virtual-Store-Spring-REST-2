package com.example.virtualstore.infrastructure.serializer;

import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import com.example.virtualstore.infrastructure.wrapper.ProductWrapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProductHolderSerializer extends JsonSerializer<ProductHolderWrapper> {
    @Override
    public void serialize(ProductHolderWrapper productHolderWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator = serialize(productHolderWrapper, jsonGenerator);
    }


    public JsonGenerator serialize(ProductHolderWrapper productHolderWrapper, JsonGenerator jsonGenerator) throws IOException {
        ProductHolder productHolder = productHolderWrapper.getProductHolder();

        jsonGenerator.writeStartObject();

        jsonGenerator.writeFieldName("item");
        jsonGenerator.writeStartObject();

        ProductWrapper productWrapper = new ProductWrapper(productHolder.getProduct());
        ProductSerializer productSerializer = new ProductSerializer();
        jsonGenerator.writeFieldName("product");
        jsonGenerator = productSerializer.serialize(productWrapper, jsonGenerator);

        jsonGenerator.writeObjectField("quantity", productHolder.getQuantity());
        jsonGenerator.writeObjectField("subtotal", String.format("R$ %.2f", productHolder.getSubtotal()));

        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();

        return jsonGenerator;
    }
}
