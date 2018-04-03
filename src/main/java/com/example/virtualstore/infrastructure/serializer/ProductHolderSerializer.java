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
        ProductHolder productHolder = productHolderWrapper.getProductHolder();
        ProductWrapper productWrapper = new ProductWrapper(productHolder.getProduct());
        ProductSerializer productSerializer = new ProductSerializer();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("productHolder");
        jsonGenerator.writeStartObject();
        // tenho Product product e int quantity
        // quantity consigo inserir diretamente
        // e product?
            // tentar escrever um objeto product wrapper
        jsonGenerator.writeFieldName("product");
        jsonGenerator = productSerializer.serialize(productWrapper, jsonGenerator);
        jsonGenerator.writeObjectField("quantity", productHolder.getQuantity());

        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();



    }
}
