package com.microservicios.wastemanageraddressservice.infra.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GlobalJsonLocalTimeSerializer extends JsonSerializer<LocalTime> {

    @Override
    public void serialize(LocalTime date, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeString(date.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}

