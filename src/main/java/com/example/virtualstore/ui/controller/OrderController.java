package com.example.virtualstore.ui.controller;


import com.example.virtualstore.application.service.OrderService;
import com.example.virtualstore.infrastructure.wrapper.OrderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<OrderWrapper> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public OrderWrapper getOrder(@PathVariable int id) {
        return orderService.getOrderWrapper(id);
    }

    @RequestMapping(value = "/clients/{clientId}/orders", method = RequestMethod.GET)
    public List<OrderWrapper> getClientOrder(@PathVariable int clientId) {
        return orderService.getClientOrders(clientId);
    }


}
