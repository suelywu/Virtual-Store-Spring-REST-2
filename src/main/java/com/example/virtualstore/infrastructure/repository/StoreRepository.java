package com.example.virtualstore.infrastructure.repository;

import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class StoreRepository {

    private List<ProductHolder> productHolders;

    @Autowired
    public StoreRepository(ProductRepository productRepository) {
        productHolders = new LinkedList<>();
        for (int i = 1; i <= 4; i++) {
            Product product = productRepository.findById(i);
            ProductHolder productHolder = new ProductHolder(product, 20);
            productHolders.add(productHolder);
        }
    }

    public List<ProductHolder> findAll() {
        return Collections.unmodifiableList(productHolders);
    }

    private Optional<ProductHolder> getProductHolderOpt(int productId) {
        return productHolders.stream().filter(productHolder -> productHolder.getProduct().getId() == productId).findFirst();
    }

    private Optional<ProductHolder> getProductHolderOpt(Product product) {
        return getProductHolderOpt(product.getId());
    }

    public boolean hasEnough(Product product, int quantity) {
        Optional<ProductHolder> productHolderOpt = getProductHolderOpt(product);
        if (productHolderOpt.isPresent()) {
            ProductHolder productHolder = productHolderOpt.get();
            return productHolder.getQuantity() >= quantity;
        }
        return false;
    }

    public void decreaseProdQuantity(Product product, int quantity) {
        Optional<ProductHolder> productHolderOpt = getProductHolderOpt(product);
        if (productHolderOpt.isPresent()) {
            ProductHolder productHolder = productHolderOpt.get();
            productHolder.decreaseQuantity(quantity);
            if (productHolder.getQuantity() == 0) {
                remove(productHolder);
            }
        }
    }

    public void increaseProdQuantity(Product product, int quantity) {
        Optional<ProductHolder> productHolderOpt = getProductHolderOpt(product);
        if (productHolderOpt.isPresent()) {
            ProductHolder productHolder = productHolderOpt.get();
            productHolder.increaseQuantity(quantity);
        }
    }

    public void save(ProductHolder productHolder) {
        productHolders.add(productHolder);
    }

    private void remove(ProductHolder productHolder) {
        Optional<ProductHolder> productHolderOpt = getProductHolderOpt(productHolder.getProduct());
        productHolderOpt.ifPresent(productHolder1 -> productHolders.remove(productHolder1));
    }

    public ProductHolder findByProductId(int productId) {
        Optional<ProductHolder> productHolderOpt = getProductHolderOpt(productId);
        return productHolderOpt.orElseGet(() -> new ProductHolder(new Product(0, "", 0), 0));
    }
}
