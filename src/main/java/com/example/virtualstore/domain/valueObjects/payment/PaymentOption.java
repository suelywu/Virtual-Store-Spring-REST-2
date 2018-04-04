package com.example.virtualstore.domain.valueObjects.payment;

public enum PaymentOption {

    BILLET("billet"), CREDIT_CARD("credit card");

    private String text;

    PaymentOption(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
