package com.example.virtualstore.domain.valueObjects.payment;

import java.util.HashMap;
import java.util.Map;

public class Billet implements Payment {

    private final PaymentInformation paymentInformation;

    public Billet(double total) {
        BarCode barCode = new BarCode(total);
        this.paymentInformation = setPaymentInformation(barCode);
    }

    public Billet(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    private PaymentInformation setPaymentInformation(BarCode barCode) {
        Map<PaymentInfoType, Object> information = new HashMap<>();
        information.put(PaymentInfoType.BAR_CODE, barCode);
        return new PaymentInformation(PaymentOption.BILLET, information);
    }

    @Override
    public PaymentOption getPaymentOption() {
        return PaymentOption.BILLET;
    }

    @Override
    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }
}
