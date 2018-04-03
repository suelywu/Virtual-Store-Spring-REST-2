package com.example.virtualstore.service;

import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.infrastructure.repository.ProductRepository;
import com.example.virtualstore.infrastructure.repository.StoreRepository;
import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StoreService {

    private StoreRepository storeRepository;
    private ProductRepository productRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository, ProductRepository productRepository) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    public List<ProductHolderWrapper> getStore() {
        List<ProductHolder> productHolders = storeRepository.findAll();
        List<ProductHolderWrapper> productHolderWrappers = new LinkedList<>();
        for (ProductHolder productHolder : productHolders) {
            productHolderWrappers.add(new ProductHolderWrapper(productHolder));
        }
        return productHolderWrappers;
    }


    public boolean hasEnough(int productId, int quantity) {
        Product product = productRepository.findById(productId);
        return storeRepository.hasEnough(product, quantity);
    }

    public void decreaseProdQuantity(int productId, int quantity) {
        Product product = productRepository.findById(productId);
        storeRepository.decreaseProdQuantity(product, quantity);
    }

    public void increaseProdQuantity(int productId, int quantity) {
        Product product = productRepository.findById(productId);
        storeRepository.increaseProdQuantity(product, quantity);
    }

    public void addNewProductToStore(int productId, int quantity) {
        Product product = productRepository.findById(productId);
        ProductHolder productHolder = new ProductHolder(product, quantity);
        storeRepository.save(productHolder);
    }


}
