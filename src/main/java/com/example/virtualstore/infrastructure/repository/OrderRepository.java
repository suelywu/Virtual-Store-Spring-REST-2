package com.example.virtualstore.infrastructure.repository;

import com.example.virtualstore.domain.entity.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class OrderRepository {

    private List<Order> orders;

    public OrderRepository() {
        orders = new LinkedList<>();
    }

    public List<Order> findAll() {
        return Collections.unmodifiableList(orders);
    }

    public Order findById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst().orElse(new Order(0,0, null, null, null));
    }

    public List<Order> findByClientId(int clientId) {
        List<Order> orderList = new LinkedList<>();
        orders.stream().filter(order -> order.getClientId() == clientId).forEach(orderList::add);
        return orderList;
    }

}
