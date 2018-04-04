package com.example.virtualstore.infrastructure.serializer;

import com.example.virtualstore.domain.valueObjects.payment.BarCode;
import com.example.virtualstore.infrastructure.wrapper.BarCodeWrapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class BarCodeSerializer extends JsonSerializer<BarCodeWrapper> {

    @Override
    public void serialize(BarCodeWrapper barCodeWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator = jsonGenerator;
    }

    public JsonGenerator serialize(BarCodeWrapper barCodeWrapper, JsonGenerator jsonGenerator) throws IOException {
        BarCode barCode = barCodeWrapper.getBarCode();
        jsonGenerator.writeStartObject();
        List<String> subCodes = barCode.getCode();
        StringBuilder finalCode = new StringBuilder();
        for (String subCode : subCodes) {
            finalCode.append(subCode).append(" ");
        }
        jsonGenerator.writeObjectField("code", finalCode.toString());
        jsonGenerator.writeEndObject();
        return jsonGenerator;
    }
}
