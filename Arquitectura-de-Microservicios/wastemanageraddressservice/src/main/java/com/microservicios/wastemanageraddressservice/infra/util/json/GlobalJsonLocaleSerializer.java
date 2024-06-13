package com.microservicios.wastemanageraddressservice.infra.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Locale;

public class GlobalJsonLocaleSerializer extends JsonSerializer<Locale> {

    @Override
    public void serialize(Locale locale, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeString(locale.toString());
    }
}

