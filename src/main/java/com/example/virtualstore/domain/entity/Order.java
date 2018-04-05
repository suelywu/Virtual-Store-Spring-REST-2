package com.example.virtualstore.domain.entity;

import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.domain.valueObjects.payment.Payment;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private final int id;
    private final int clientId;
    private final LocalDateTime dateTime;
    private final List<ProductHolder> productHolders;
    private final Payment payment;

    public Order(final int id, final int clientId, final List<ProductHolder> productHolders, final Payment payment) {
        this(id, clientId, LocalDateTime.now(), productHolders, payment);
    }

    public Order(final int id, final int clientId, final LocalDateTime dateTime, final List<ProductHolder> productHolders, final Payment payment) {
        this.id = id;
        this.clientId = clientId;
        this.dateTime = dateTime;
        this.productHolders = productHolders;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<ProductHolder> getProductHolders() {
        return productHolders;
    }

    public Payment getPayment() {
        return payment;
    }
}
