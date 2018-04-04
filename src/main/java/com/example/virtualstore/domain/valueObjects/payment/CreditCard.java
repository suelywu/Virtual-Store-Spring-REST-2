package com.example.virtualstore.domain.valueObjects.payment;

import java.util.HashMap;
import java.util.Map;

public class CreditCard implements Payment {

    private final PaymentInformation paymentInformation;

    public CreditCard(int cardNumber, double total, int timesToPay) {
        this.paymentInformation = setPaymentInformation(cardNumber, total, timesToPay);
    }

    public CreditCard(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    private PaymentInformation setPaymentInformation(int cardNumber, double total, int timesToPay) {
        Map<PaymentInfoType, Object> information = new HashMap<>();
        information.put(PaymentInfoType.CARD_NUMBER, cardNumber);
        information.put(PaymentInfoType.TIMES_TO_PAY, timesToPay);
        information.put(PaymentInfoType.VALUE_TO_PAY_AT_A_TIME, total/timesToPay);
        return new PaymentInformation(PaymentOption.CREDIT_CARD, information);
    }



    @Override
    public PaymentOption getPaymentOption() {
        return PaymentOption.CREDIT_CARD;
    }

    @Override
    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }
}
