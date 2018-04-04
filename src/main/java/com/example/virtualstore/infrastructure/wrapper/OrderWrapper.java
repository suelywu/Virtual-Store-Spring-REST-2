package com.example.virtualstore.infrastructure.wrapper;

import com.example.virtualstore.domain.entity.Order;
import com.example.virtualstore.infrastructure.deserializer.OrderDeserializer;
import com.example.virtualstore.infrastructure.serializer.OrderSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = OrderSerializer.class)
@JsonDeserialize(using = OrderDeserializer.class)
public class OrderWrapper {

    private final Order order;

    public OrderWrapper(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

}
