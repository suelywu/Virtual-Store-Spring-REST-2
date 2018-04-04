package com.example.virtualstore.infrastructure.deserializer;

import com.example.virtualstore.infrastructure.wrapper.ClientWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ClientDeserializer extends JsonDeserializer<ClientWrapper> {
    @Override
    public ClientWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return null;
    }
}
