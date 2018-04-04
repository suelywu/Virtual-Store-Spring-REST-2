package com.example.virtualstore.infrastructure.wrapper;

import com.example.virtualstore.domain.valueObjects.payment.PaymentInformation;
import com.example.virtualstore.infrastructure.deserializer.PaymentInfoDeserializer;
import com.example.virtualstore.infrastructure.serializer.PaymentInfoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = PaymentInfoSerializer.class)
@JsonDeserialize(using = PaymentInfoDeserializer.class)
public class PaymentInfoWrapper {

    private final PaymentInformation paymentInformation;

    public PaymentInfoWrapper(final PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }
}
