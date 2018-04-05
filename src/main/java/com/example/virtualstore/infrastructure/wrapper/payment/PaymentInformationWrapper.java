package com.example.virtualstore.infrastructure.wrapper.payment;

import com.example.virtualstore.domain.valueObjects.payment.PaymentInformation;
import com.example.virtualstore.infrastructure.deserializer.payment.PaymentInformationDeserializer;
import com.example.virtualstore.infrastructure.serializer.payment.PaymentInformationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = PaymentInformationSerializer.class)
@JsonDeserialize(using = PaymentInformationDeserializer.class)
public class PaymentInformationWrapper {

    private final PaymentInformation paymentInformation;

    public PaymentInformationWrapper(final PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }
}
