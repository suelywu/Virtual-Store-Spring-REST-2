package com.example.virtualstore.infrastructure.wrapper.payment;

import com.example.virtualstore.domain.valueObjects.payment.BarCode;
import com.example.virtualstore.infrastructure.deserializer.payment.BarCodeDeserializer;
import com.example.virtualstore.infrastructure.serializer.payment.BarCodeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = BarCodeSerializer.class)
@JsonDeserialize(using = BarCodeDeserializer.class)
public class BarCodeWrapper {

    private final BarCode barCode;


    public BarCodeWrapper(final BarCode barCode) {
        this.barCode = barCode;
    }

    public BarCode getBarCode() {
        return barCode;
    }

}
