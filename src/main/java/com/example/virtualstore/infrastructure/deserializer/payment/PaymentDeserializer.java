package com.example.virtualstore.infrastructure.deserializer.payment;

import com.example.virtualstore.domain.valueObjects.payment.Payment;
import com.example.virtualstore.domain.valueObjects.payment.PaymentFactory;
import com.example.virtualstore.domain.valueObjects.payment.PaymentInformation;
import com.example.virtualstore.domain.valueObjects.payment.PaymentOption;
import com.example.virtualstore.infrastructure.wrapper.payment.PaymentInformationWrapper;
import com.example.virtualstore.infrastructure.wrapper.payment.PaymentWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PaymentDeserializer extends JsonDeserializer<PaymentWrapper> {
    @Override
    public PaymentWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode paymentNode = jsonNode.get("payment");

        PaymentInformationDeserializer paymentInformationDeserializer = new PaymentInformationDeserializer();
        PaymentInformationWrapper paymentInformationWrapper = paymentInformationDeserializer.deserialize(paymentNode);
        PaymentInformation paymentInformation = paymentInformationWrapper.getPaymentInformation();
        PaymentFactory paymentFactory = new PaymentFactory();
        Payment payment = paymentFactory.getPayment(paymentInformation);
        return new PaymentWrapper(payment);
    }
}
