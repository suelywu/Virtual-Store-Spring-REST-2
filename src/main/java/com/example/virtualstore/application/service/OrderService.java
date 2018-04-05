package com.example.virtualstore.application.service;

import com.example.virtualstore.domain.entity.Order;
import com.example.virtualstore.domain.repository.OrderRepository;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.domain.valueObjects.payment.Payment;
import com.example.virtualstore.infrastructure.wrapper.OrderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public OrderWrapper getOrderWrapper(int id) {
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

    public int addNewOrder(int clientId, List<ProductHolder> productHolders, Payment payment) {
        int id = orderRepository.getNextIdToUse();
        Order order = new Order(id, clientId, LocalDateTime.now(), productHolders, payment);
        orderRepository.save(order);
        return id;
    }
}
