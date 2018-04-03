package com.example.virtualstore.infrastructure.repository;

import com.example.virtualstore.domain.valueObjects.ProductHolder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class StoreRepository {

    private List<ProductHolder> productHolders;

    public StoreRepository() {
        productHolders = new LinkedList<>();
    }

    public void save(ProductHolder productHolder) {
        productHolders.add(productHolder);
    }

    public void remove(ProductHolder productHolder) {
        productHolders.remove(productHolder);
    }


}
