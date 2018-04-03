package com.example.virtualstore.infrastructure.wrapper;

import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.infrastructure.deserializer.ProductDeserializer;
import com.example.virtualstore.infrastructure.serializer.ProductSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ProductSerializer.class)
@JsonDeserialize(using = ProductDeserializer.class)
public class ProductHolderWrapper {

    private final ProductHolder productHolder;

    public ProductHolderWrapper(ProductHolder productHolder) {
        this.productHolder = productHolder;
    }

    public ProductHolder getProductHolder() {
        return productHolder;
    }
}
