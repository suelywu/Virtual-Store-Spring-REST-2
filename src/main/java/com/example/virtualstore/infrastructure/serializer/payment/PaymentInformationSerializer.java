package com.example.virtualstore.infrastructure.serializer.payment;

import com.example.virtualstore.domain.valueObjects.payment.BarCode;
import com.example.virtualstore.domain.valueObjects.payment.PaymentInfoType;
import com.example.virtualstore.domain.valueObjects.payment.PaymentInformation;
import com.example.virtualstore.domain.valueObjects.payment.PaymentOption;
import com.example.virtualstore.infrastructure.wrapper.payment.BarCodeWrapper;
import com.example.virtualstore.infrastructure.wrapper.payment.PaymentInformationWrapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

public class PaymentInformationSerializer extends JsonSerializer<PaymentInformationWrapper> {
    @Override
    public void serialize(PaymentInformationWrapper paymentInformationWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator = serialize(paymentInformationWrapper.getPaymentInformation(), jsonGenerator);
    }

    public JsonGenerator serialize(PaymentInformation paymentInformation, JsonGenerator jsonGenerator) throws IOException {
        Map<PaymentInfoType, Object> information = paymentInformation.getInformation();
        PaymentOption paymentOption = paymentInformation.getPaymentOption();

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("option", paymentOption.getText());

        switch (paymentOption) {
            case BILLET:
                jsonGenerator = serializeBillet(information, jsonGenerator);
                break;
            case CREDIT_CARD:
                jsonGenerator = serializeCreditCard(information, jsonGenerator);
                break;
        }


        jsonGenerator.writeEndObject();
        return jsonGenerator;
    }


    private JsonGenerator serializeBillet(Map<PaymentInfoType, Object> information, JsonGenerator jsonGenerator) throws IOException {
        BarCodeSerializer barCodeSerializer = new BarCodeSerializer();
        jsonGenerator.writeFieldName("barCode");
        BarCode barCode = (BarCode) information.get(PaymentInfoType.BAR_CODE);
        jsonGenerator = barCodeSerializer.serialize(new BarCodeWrapper(barCode), jsonGenerator);
        return jsonGenerator;
    }

    private JsonGenerator serializeCreditCard(Map<PaymentInfoType, Object> information, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeObjectField("cardNumber", information.get(PaymentInfoType.CARD_NUMBER));
        jsonGenerator.writeObjectField("timesToPay", information.get(PaymentInfoType.TIMES_TO_PAY));
        jsonGenerator.writeObjectField("valueToPayAtATime", information.get(PaymentInfoType.VALUE_TO_PAY_AT_A_TIME));
        return jsonGenerator;
    }
}
