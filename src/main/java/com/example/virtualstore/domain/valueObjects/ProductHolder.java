package com.example.virtualstore.domain.valueObjects;

import com.example.virtualstore.domain.entity.Product;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class ProductHolder {
    @JsonUnwrapped
    private final Product product;
    private int quantity;

    public ProductHolder(final Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int qtt) {
        this.quantity += qtt;
    }

    public void decreaseQuantity(int qtt) {
        if (quantity-qtt >= 0) {
            quantity -= qtt;
        }
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

}
