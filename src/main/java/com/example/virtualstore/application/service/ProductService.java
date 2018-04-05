package com.example.virtualstore.application.service;

import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.domain.repository.ProductRepository;
import com.example.virtualstore.infrastructure.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductWrapper> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductWrapper> productWrappers = new LinkedList<>();
        for (Product product : products) {
            productWrappers.add(new ProductWrapper(product));
        }
        return productWrappers;
    }

    public ProductWrapper getProduct(int id) {
        return new ProductWrapper(productRepository.findById(id));
    }

    public ProductWrapper getProduct(String name) {
        return new ProductWrapper(productRepository.findByName(name));
    }

    public void registerNewProduct(String name, double price) {
        int id = productRepository.getNextIdToUse();
        Product product = new Product(id, name, price);
        productRepository.save(product);
    }

    public int getProductId(String name) {
        return productRepository.getProductId(name);
    }

    public void removeById(int id) {
        productRepository.removeById(id);
    }

    public void remove(Product product) {
        productRepository.remove(product);
    }

    public boolean hasProduct(int id) {
        return productRepository.contains(id);
    }
}
