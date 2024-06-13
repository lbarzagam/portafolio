package com.microservicios.wastemanagerservice.infra.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GlobalJsonLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jp.getCurrentTokenId() == JsonTokenId.ID_STRING) {
            try {
                return LocalTime.parse(jp.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            }
            catch (DateTimeParseException e) {
                return LocalTime.parse(jp.getText(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
        }
        return null;
    }
}
