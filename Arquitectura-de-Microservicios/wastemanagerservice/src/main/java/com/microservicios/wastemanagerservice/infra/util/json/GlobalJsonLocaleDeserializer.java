package com.microservicios.wastemanagerservice.infra.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Locale;

public class GlobalJsonLocaleDeserializer extends JsonDeserializer<Locale> {

    @Override
    public Locale deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.getCurrentTokenId() == JsonTokenId.ID_STRING) {
            return new Locale(jp.getText());
        }
        return null;
    }
}

