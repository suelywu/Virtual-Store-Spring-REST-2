package com.example.virtualstore.infrastructure.repository;

import com.example.virtualstore.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {

    List<Product> products;

    public ProductRepository() {
        products = new LinkedList<>();
        products.add(new Product(1, "mesa", 300));
        products.add(new Product(2, "cadeira", 100));
        products.add(new Product(3, "camiseta", 30));
        products.add(new Product(4, "tenis", 100));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product findById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(new Product(0, "", 0));
    }

    public Product findByName(String name) {
        return products.stream().filter(product -> product.getName().equalsIgnoreCase(name)).findFirst().orElse(new Product(0, "", 0));
    }

    public int getProductId(String name) {
        return products.stream().filter(product -> product.getName().equalsIgnoreCase(name)).findFirst().orElse(new Product(0, "", 0)).getId();
    }

    public int getNextIdToUse() {
        return products.size()+1;
    }

    public void save(Product product) {
        products.add(product);
    }

    public void remove(Product product) {
        Optional<Product> productOpt = products.stream().filter(p -> p.getId() == product.getId()).findFirst();
        if (productOpt.isPresent()) {
            Product productToRemove = productOpt.get();
            products.remove(productToRemove);
        }
    }

    public void removeById(int id) {
        Optional<Product> productOpt = products.stream().filter(p -> p.getId() == id).findFirst();
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            products.remove(product);
        }
    }

}
