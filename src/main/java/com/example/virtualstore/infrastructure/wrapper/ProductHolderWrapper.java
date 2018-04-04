package com.example.virtualstore.infrastructure.wrapper;

import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.infrastructure.deserializer.ProductHolderDeserializer;
import com.example.virtualstore.infrastructure.serializer.ProductHolderSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ProductHolderSerializer.class)
@JsonDeserialize(using = ProductHolderDeserializer.class)
public class ProductHolderWrapper {

    private final ProductHolder productHolder;

    public ProductHolderWrapper(ProductHolder productHolder) {
        this.productHolder = productHolder;
    }

    public ProductHolder getProductHolder() {
        return productHolder;
    }
}
