package com.products.backend.infra.util.json;

import com.products.backend.infra.util.datetime.Util_DateTime;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;

public class GlobalJsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jp.getCurrentTokenId() == JsonTokenId.ID_STRING) {
            return Util_DateTime.deserializarFechaHoraAsLocalDate(jp.getText());
        }
        return null;
    }
}

