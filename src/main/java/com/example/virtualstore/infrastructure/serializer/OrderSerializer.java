package com.example.virtualstore.infrastructure.serializer;

import com.example.virtualstore.domain.entity.Order;
import com.example.virtualstore.domain.valueObjects.ProductHolder;
import com.example.virtualstore.domain.valueObjects.payment.Payment;
import com.example.virtualstore.domain.valueObjects.payment.PaymentInformation;
import com.example.virtualstore.infrastructure.serializer.payment.PaymentInformationSerializer;
import com.example.virtualstore.infrastructure.wrapper.OrderWrapper;
import com.example.virtualstore.infrastructure.wrapper.ProductHolderWrapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderSerializer extends JsonSerializer<OrderWrapper> {
    @Override
    public void serialize(OrderWrapper orderWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator = serialize(orderWrapper, jsonGenerator);
    }

    public JsonGenerator serialize(OrderWrapper orderWrapper, JsonGenerator jsonGenerator) throws IOException {
        Order order = orderWrapper.getOrder();
        List<ProductHolder> productHolders = order.getProductHolders();
        ProductHolderSerializer productHolderSerializer = new ProductHolderSerializer();
        PaymentInformationSerializer paymentInformationSerializer = new PaymentInformationSerializer();
        Payment payment = order.getPayment();
        PaymentInformation paymentInformation = payment.getPaymentInformation();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
        String dateTimeString = order.getDateTime().format(formatter);
        double total = 0;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("order");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("id", order.getId());
        jsonGenerator.writeObjectField("clientId", order.getClientId());
        jsonGenerator.writeObjectField("dateTime", dateTimeString);
        jsonGenerator.writeFieldName("products");
        jsonGenerator.writeStartArray();
        for (ProductHolder productHolder : productHolders) {
            ProductHolderWrapper productHolderWrapper = new ProductHolderWrapper(productHolder);
            jsonGenerator = productHolderSerializer.serialize(productHolderWrapper, jsonGenerator);
            total += productHolder.getSubtotal();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeObjectField("total", String.format("R$ %.2f",total));

        jsonGenerator.writeFieldName("payment");
        jsonGenerator = paymentInformationSerializer.serialize(paymentInformation, jsonGenerator);

        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
        return jsonGenerator;
    }
}
