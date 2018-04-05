package com.example.virtualstore.infrastructure.serializer.payment;

import com.example.virtualstore.domain.valueObjects.payment.BarCode;
import com.example.virtualstore.infrastructure.wrapper.payment.BarCodeWrapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class BarCodeSerializer extends JsonSerializer<BarCodeWrapper> {

    @Override
    public void serialize(BarCodeWrapper barCodeWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator = serialize(barCodeWrapper, jsonGenerator);
    }

    public JsonGenerator serialize(BarCodeWrapper barCodeWrapper, JsonGenerator jsonGenerator) throws IOException {
        BarCode barCode = barCodeWrapper.getBarCode();
        List<String> subCodes = barCode.getCode();
        StringBuilder finalCode = new StringBuilder();
        for (String subCode : subCodes) {
            finalCode.append(subCode).append(" ");
        }
        jsonGenerator.writeObject(finalCode.toString());
        return jsonGenerator;
    }
}
