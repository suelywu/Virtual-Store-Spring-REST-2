package com.example.virtualstore.infrastructure.wrapper;


import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.infrastructure.deserializer.ProductDeserializer;
import com.example.virtualstore.infrastructure.serializer.ProductSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ProductSerializer.class)
@JsonDeserialize(using = ProductDeserializer.class)
public class ProductWrapper {

    private Product product;

    public ProductWrapper(Product product) {
        super();
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
