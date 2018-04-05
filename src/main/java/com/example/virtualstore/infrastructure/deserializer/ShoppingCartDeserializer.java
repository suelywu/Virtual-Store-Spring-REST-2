package com.example.virtualstore.infrastructure.deserializer;

import com.example.virtualstore.domain.entity.ShoppingCart;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import com.example.virtualstore.infrastructure.wrapper.ShoppingCartWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCartDeserializer extends JsonDeserializer<ShoppingCartWrapper> {
    @Override
    public ShoppingCartWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        List<ProductHolder> productHolders = new LinkedList<>();
        ProductHolderDeserializer productHolderDeserializer = new ProductHolderDeserializer();

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        int clientId = jsonNode.get("clientId").asInt();

        JsonNode selectedProdNode = jsonNode.get("selectProducts");

        JsonNode prodHolderNode = selectedProdNode.get("item");
        while (prodHolderNode != null) {
            ProductHolderWrapper productHolderWrapper = productHolderDeserializer.deserialize(prodHolderNode);
            productHolders.add(productHolderWrapper.getProductHolder());
        }

        ShoppingCart shoppingCart = new ShoppingCart(clientId);
        productHolders.forEach(shoppingCart::addProduct);

        return new ShoppingCartWrapper(shoppingCart);

    }
}
