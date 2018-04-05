package com.example.virtualstore.domain.repository;

import com.example.virtualstore.domain.entity.Order;
import com.example.virtualstore.domain.entity.Product;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.domain.valueObjects.payment.Billet;
import com.example.virtualstore.domain.valueObjects.payment.CreditCard;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class OrderRepository {

    private List<Order> orders;

    public OrderRepository() {
        orders = new LinkedList<>();
    }

    private void orderTest() {
        List<ProductHolder> productHolders = new LinkedList<>();
        productHolders.add(new ProductHolder(new Product(1, "prod1", 100), 1));
        productHolders.add(new ProductHolder(new Product(2, "prod2", 200), 2));
        Billet billet = new Billet(500);
        CreditCard creditCard = new CreditCard(1234, 500, 2);
        orders.add(new Order(1, 1, LocalDateTime.now(), productHolders, billet));
        orders.add(new Order(2, 2, LocalDateTime.now(), productHolders, creditCard));
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

    public void save(Order order) {
        this.orders.add(order);
    }

    public int getNextIdToUse() {
        return orders.size()+1;
    }

}
