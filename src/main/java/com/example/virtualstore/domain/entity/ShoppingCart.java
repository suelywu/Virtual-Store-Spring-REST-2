package com.example.virtualstore.domain.entity;

import com.example.virtualstore.domain.valueObjects.ProductHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class ShoppingCart {

    List<ProductHolder> productHolders;

    public ShoppingCart() {
        this.productHolders = new LinkedList<>();
    }

    public List<ProductHolder> getProductHolders() {
        return Collections.unmodifiableList(productHolders);
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

}
