package com.example.virtualstore.infrastructure.wrapper.payment;

import com.example.virtualstore.domain.valueObjects.payment.Payment;
import com.example.virtualstore.infrastructure.deserializer.payment.PaymentDeserializer;
import com.example.virtualstore.infrastructure.serializer.payment.PaymentSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = PaymentSerializer.class)
@JsonDeserialize(using = PaymentDeserializer.class)
public class PaymentWrapper {

    private Payment payment;

    public PaymentWrapper(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

}
