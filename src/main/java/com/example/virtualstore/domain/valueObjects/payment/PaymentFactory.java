package com.example.virtualstore.domain.valueObjects.payment;

public class PaymentFactory {

    public Payment getPayment(PaymentInformation paymentInformation) {
        PaymentOption paymentOption = paymentInformation.getPaymentOption();
        switch (paymentOption) {
            case BILLET:
                return new Billet(paymentInformation);
            case CREDIT_CARD:
                return new CreditCard(paymentInformation);
        }
        return null;
    }


}
