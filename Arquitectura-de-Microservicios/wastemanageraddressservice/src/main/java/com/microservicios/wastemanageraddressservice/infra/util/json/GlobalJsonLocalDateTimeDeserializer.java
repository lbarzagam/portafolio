package com.microservicios.wastemanageraddressservice.infra.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.microservicios.wastemanageraddressservice.infra.util.datetime.Util_DateTime;

import java.io.IOException;
import java.time.LocalDateTime;

public class GlobalJsonLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.getCurrentTokenId() == JsonTokenId.ID_STRING) {
            return Util_DateTime.deserializarFechaHora(jp.getText(), true);
        }
        return null;
    }
}

