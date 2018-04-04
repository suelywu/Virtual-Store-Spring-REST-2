package com.example.virtualstore.domain.entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Client {

    private final int id;
    private final String name;
    private List<Order> orders;

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
        this.orders = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

}
