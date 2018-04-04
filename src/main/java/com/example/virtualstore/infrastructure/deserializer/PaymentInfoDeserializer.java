package com.example.virtualstore.infrastructure.deserializer;

import com.example.virtualstore.domain.valueObjects.payment.PaymentInfoType;
import com.example.virtualstore.domain.valueObjects.payment.PaymentInformation;
import com.example.virtualstore.domain.valueObjects.payment.PaymentOption;
import com.example.virtualstore.infrastructure.wrapper.BarCodeWrapper;
import com.example.virtualstore.infrastructure.wrapper.PaymentInfoWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PaymentInfoDeserializer extends JsonDeserializer<PaymentInfoWrapper> {
    @Override
    public PaymentInfoWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(jsonNode.get("payment"));
    }

    public PaymentInfoWrapper deserialize(JsonNode paymentNode) {
        PaymentOption paymentOption = PaymentOption.valueOf(paymentNode.get("option").asText());
        Map<PaymentInfoType, Object> information = null;
        switch (paymentOption) {
            case BILLET:
                information = deserializeInformationBillet(paymentNode);
                break;
            case CREDIT_CARD:
                information = deserializeInformationCreditCard(paymentNode);
                break;
        }
        PaymentInformation paymentInformation = new PaymentInformation(paymentOption, information);
        return new PaymentInfoWrapper(paymentInformation);
    }

    private Map<PaymentInfoType, Object> deserializeInformationBillet(JsonNode billetInfoNode) {
        Map<PaymentInfoType, Object> information = new HashMap<>();
        BarCodeDeserializer barCodeDeserializer = new BarCodeDeserializer();
        BarCodeWrapper barCodeWrapper = barCodeDeserializer.deserialize(billetInfoNode.get("barCode"));
        information.put(PaymentInfoType.BAR_CODE, barCodeWrapper.getBarCode());
        return information;
    }

    private Map<PaymentInfoType, Object> deserializeInformationCreditCard(JsonNode creditCardInfoNode) {
        Map<PaymentInfoType, Object> information = new HashMap<>();
        int cardNumber = creditCardInfoNode.get("cardNumber").asInt();
        int timesToPay = creditCardInfoNode.get("timesToPay").asInt();
        double valueToPayAtATime = creditCardInfoNode.get("valueToPayAtATime").asDouble();
        information.put(PaymentInfoType.CARD_NUMBER, cardNumber);
        information.put(PaymentInfoType.TIMES_TO_PAY, timesToPay);
        information.put(PaymentInfoType.VALUE_TO_PAY_AT_A_TIME, valueToPayAtATime);
        return information;
    }

}
