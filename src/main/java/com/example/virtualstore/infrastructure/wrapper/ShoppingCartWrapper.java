package com.example.virtualstore.infrastructure.wrapper;

import com.example.virtualstore.domain.entity.ShoppingCart;
import com.example.virtualstore.infrastructure.deserializer.ShoppingCartDeserializer;
import com.example.virtualstore.infrastructure.serializer.ShoppingCartSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ShoppingCartSerializer.class)
@JsonDeserialize(using = ShoppingCartDeserializer.class)
public class ShoppingCartWrapper {

    private ShoppingCart shoppingCart;

    public ShoppingCartWrapper(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
