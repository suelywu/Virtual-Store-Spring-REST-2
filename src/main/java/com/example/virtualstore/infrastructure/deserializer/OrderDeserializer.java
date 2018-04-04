package com.example.virtualstore.infrastructure.deserializer;

import com.example.virtualstore.domain.entity.Order;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.domain.valueObjects.payment.Payment;
import com.example.virtualstore.domain.valueObjects.payment.PaymentFactory;
import com.example.virtualstore.domain.valueObjects.payment.PaymentInformation;
import com.example.virtualstore.infrastructure.wrapper.OrderWrapper;
import com.example.virtualstore.infrastructure.wrapper.PaymentInfoWrapper;
import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class OrderDeserializer extends JsonDeserializer<OrderWrapper> {
    @Override
    public OrderWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode orderNode = jsonNode.get("order");
        return deserialize(orderNode);
    }

    public OrderWrapper deserialize(JsonNode orderNode) {
        List<ProductHolder> productHolders = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");

        int id = orderNode.get("id").asInt();

        int clientId = orderNode.get("clientId").asInt();

        String dateTimeString = orderNode.get("dateTime").asText();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        JsonNode productsNode = orderNode.get("products");
        for (JsonNode prodHolderNode : productsNode) {
            ProductHolder productHolder = deserializeProductHolder(prodHolderNode);
            productHolders.add(productHolder);
        }

        JsonNode paymentNode = orderNode.get("payment");
        Payment payment = deserializePayment(paymentNode);

        Order order = new Order(id, clientId, dateTime, productHolders, payment);
        return new OrderWrapper(order);
    }

    private ProductHolder deserializeProductHolder(JsonNode prodHolderNode) {
        ProductHolderDeserializer productHolderDeserializer = new ProductHolderDeserializer();
        ProductHolderWrapper productHolderWrapper = productHolderDeserializer.deserialize(prodHolderNode);
        return productHolderWrapper.getProductHolder();
    }

    private Payment deserializePayment(JsonNode paymentNode) {
        PaymentInfoDeserializer paymentInfoDeserializer = new PaymentInfoDeserializer();
        PaymentInfoWrapper paymentInfoWrapper = paymentInfoDeserializer.deserialize(paymentNode);
        PaymentInformation paymentInformation = paymentInfoWrapper.getPaymentInformation();
        PaymentFactory paymentFactory = new PaymentFactory();
        return paymentFactory.getPayment(paymentInformation.getPaymentOption(), paymentInformation);
    }
}
