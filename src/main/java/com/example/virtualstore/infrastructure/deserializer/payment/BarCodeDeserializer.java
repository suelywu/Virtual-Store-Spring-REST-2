package com.example.virtualstore.infrastructure.deserializer.payment;

import com.example.virtualstore.domain.valueObjects.payment.BarCode;
import com.example.virtualstore.infrastructure.wrapper.payment.BarCodeWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BarCodeDeserializer extends JsonDeserializer<BarCodeWrapper> {
    @Override
    public BarCodeWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(jsonNode);
    }

    public BarCodeWrapper deserialize(JsonNode jsonNode) {
        String finalCode = jsonNode.asText();
        List<String> codes = Arrays.asList(finalCode.split(" "));
        BarCode barCode = new BarCode(0);
        barCode.setCode(codes);
        return new BarCodeWrapper(barCode);
    }
}
