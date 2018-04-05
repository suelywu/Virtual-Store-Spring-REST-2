package com.example.virtualstore.infrastructure.serializer.payment;

import com.example.virtualstore.domain.valueObjects.payment.Payment;
import com.example.virtualstore.domain.valueObjects.payment.PaymentInformation;
import com.example.virtualstore.domain.valueObjects.payment.PaymentOption;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PaymentSerializer extends JsonSerializer<Payment> {
    @Override
    public void serialize(Payment payment, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator = serialize(payment, jsonGenerator);
    }


    public JsonGenerator serialize(Payment payment, JsonGenerator jsonGenerator) throws IOException {
        PaymentOption paymentOption = payment.getPaymentOption();
        PaymentInformation paymentInformation = payment.getPaymentInformation();
        PaymentInformationSerializer paymentInformationSerializer = new PaymentInformationSerializer();

        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("payment");
        jsonGenerator = paymentInformationSerializer.serialize(paymentInformation, jsonGenerator);
        jsonGenerator.writeEndObject();

        return jsonGenerator;
    }



}
