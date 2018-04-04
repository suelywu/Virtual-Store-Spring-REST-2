package com.example.virtualstore.domain.valueObjects.payment;

import java.util.Map;

public class PaymentInformation {

    private final PaymentOption paymentOption;
    private final Map<PaymentInfoType, Object> information;


    public PaymentInformation(final PaymentOption paymentOption, final Map<PaymentInfoType, Object> information) {
        this.paymentOption = paymentOption;
        this.information = information;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public Map<PaymentInfoType, Object> getInformation() {
        return information;
    }
}
