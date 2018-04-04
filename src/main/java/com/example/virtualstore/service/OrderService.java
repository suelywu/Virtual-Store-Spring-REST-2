package com.example.virtualstore.service;

import com.example.virtualstore.domain.entity.Order;
import com.example.virtualstore.infrastructure.repository.OrderRepository;
import com.example.virtualstore.infrastructure.wrapper.OrderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderWrapper> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderWrapper> orderWrappers = new LinkedList<>();
        for (Order order : orders) {
            orderWrappers.add(new OrderWrapper(order));
        }
        return orderWrappers;
    }

    public OrderWrapper getOrder(int id) {
        return new OrderWrapper(orderRepository.findById(id));
    }

    public List<OrderWrapper> getClientOrders(int clientId) {
        List<Order> orders = orderRepository.findByClientId(clientId);
        List<OrderWrapper> orderWrappers = new LinkedList<>();
        for (Order order : orders) {
            orderWrappers.add(new OrderWrapper(order));
        }
        return orderWrappers;
    }
}
