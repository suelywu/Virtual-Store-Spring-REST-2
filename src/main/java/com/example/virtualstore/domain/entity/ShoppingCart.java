package com.example.virtualstore.domain.entity;

import com.example.virtualstore.domain.valueObjects.ProductHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {

    private final int clientId;
    private List<ProductHolder> productHolders;

    public ShoppingCart(int clientId) {
        this.clientId = clientId;
        this.productHolders = new LinkedList<>();
    }

    public List<ProductHolder> getProductHolders() {
        return Collections.unmodifiableList(productHolders);
    }

    public int getClientId() {
        return clientId;
    }

    public void addProduct(ProductHolder productHolder) {
        addProduct(productHolder.getProduct(), productHolder.getQuantity());
    }

    public void addProduct(Product product, int quantity) {
        ProductHolder productHolder = getProductHolder(product);
        if (productHolder == null) {
            productHolder = new ProductHolder(product, quantity);
            productHolders.add(productHolder);
            return;
        }
        productHolder.increaseQuantity(quantity);
    }

    public boolean delProduct(ProductHolder productHolder) {
        Product product = productHolder.getProduct();
        int quantity = productHolder.getQuantity();
        return delProduct(product.getId(), quantity);
    }

    public boolean delProduct(int productId, int quantity) {
        ProductHolder productHolder = getProductHolder(productId);
        if (productHolder != null) {
            if (productHolder.getQuantity() >= quantity) {
                productHolder.decreaseQuantity(quantity);
                return true;
            }
            return false;
        }
        return false;
    }

    private ProductHolder getProductHolder(Product product) {
        return getProductHolder(product.getId());
    }

    private ProductHolder getProductHolder(int productId) {
        Optional<ProductHolder> productHolderOpt = productHolders.stream().filter(productHolder -> productHolder.getProduct().getId() == productId).findFirst();
        return productHolderOpt.orElse(null);
    }

    public void clear() {
        this.productHolders = new LinkedList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Integer) {
            return this.clientId == (int) obj;
        }
        return super.equals(obj);
    }
}
