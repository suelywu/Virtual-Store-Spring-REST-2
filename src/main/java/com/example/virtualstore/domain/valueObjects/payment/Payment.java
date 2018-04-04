package com.example.virtualstore.domain.valueObjects.payment;

public interface Payment {

    PaymentOption getPaymentOption();
    PaymentInformation getPaymentInformation();

}
