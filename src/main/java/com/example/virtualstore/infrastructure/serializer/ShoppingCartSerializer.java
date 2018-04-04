package com.example.virtualstore.infrastructure.serializer;

import com.example.virtualstore.domain.entity.ShoppingCart;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import com.example.virtualstore.infrastructure.wrapper.ShoppingCartWrapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class ShoppingCartSerializer extends JsonSerializer<ShoppingCartWrapper> {
    @Override
    public void serialize(ShoppingCartWrapper shoppingCartWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ShoppingCart shoppingCart = shoppingCartWrapper.getShoppingCart();
        List<ProductHolder> productHolders = shoppingCart.getProductHolders();
        double total = 0;

        jsonGenerator.writeStartObject();

        jsonGenerator.writeFieldName("selectedProducts");
        jsonGenerator.writeStartArray();
        ProductHolderSerializer productHolderSerializer = new ProductHolderSerializer();
        for (ProductHolder productHolder : productHolders) {
            ProductHolderWrapper wrapper = new ProductHolderWrapper(productHolder);
            jsonGenerator = productHolderSerializer.serialize(wrapper, jsonGenerator);
            total += productHolder.getSubtotal();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeObjectField("total", String.format("R$ %.2f", total));

        jsonGenerator.writeEndObject();

    }
}
