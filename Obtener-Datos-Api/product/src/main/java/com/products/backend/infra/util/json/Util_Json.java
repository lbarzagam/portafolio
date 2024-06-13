package com.products.backend.infra.util.json;

import com.products.backend.domain.validators.CodigoErrorEnum;
import com.products.backend.infra.exceptions.DomainException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Map;

public class Util_Json {

    private Util_Json() {
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Registering Hibernate4Module to support lazy objects
        Hibernate5Module hibModule = new Hibernate5Module();
        hibModule.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        objectMapper.registerModule(hibModule);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //Support for Java 8 DateTime API
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule("", Version.unknownVersion());
        module.addSerializer(LocalDate.class, new GlobalJsonLocalDateSerializer());
        module.addDeserializer(LocalDate.class, new GlobalJsonLocalDateDeserializer());
        module.addSerializer(LocalDateTime.class, new GlobalJsonLocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new GlobalJsonLocalDateTimeDeserializer());
        module.addSerializer(LocalTime.class, new GlobalJsonLocalTimeSerializer());
        module.addDeserializer(LocalTime.class, new GlobalJsonLocalTimeDeserializer());
        module.addSerializer(Number.class, new GlobalJsonNumberSerializer());
        module.addSerializer(Locale.class, new GlobalJsonLocaleSerializer());
        module.addDeserializer(Locale.class, new GlobalJsonLocaleDeserializer());
        module.addSerializer(ZonedDateTime.class, new GlobalJsonZonedDateTimeSerializer());
        module.addDeserializer(ZonedDateTime.class, new GlobalJsonZonedDateTimeDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

    @SneakyThrows
    public static <T> String toJSON(T obj) {
        return  Util_Json.getObjectMapper().writeValueAsString(obj);
    }

    public static <T> void toJSON(T obj, File outputFile) throws Exception {
         Util_Json.getObjectMapper().writeValue(outputFile, obj);
    }

    @SneakyThrows
    public static <T extends Object> T fromJSON(String json, Class<T> _class) {
        return  Util_Json.getObjectMapper().readValue(json, _class);
    }

    public static <T extends Serializable> T fromJSON(File jsonFile, Class<T> _class) throws Exception {
        return  Util_Json.getObjectMapper().readValue(jsonFile, _class);
    }

    public static String writeValueAsString(Object value) {
        try {
            return getObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new DomainException(CodigoErrorEnum.ERROR_CONVIRTIENDO_OBJ_A_JSON);
        }
    }

    public static Map writeValueAsMap(Object value) {
        return getObjectMapper().convertValue(value, Map.class);
    }

    public static String writeValueAsStringWithoutIdentOutput(Object value) {
        try {
            ObjectMapper objectMapper = getObjectMapper();
            objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new DomainException(CodigoErrorEnum.ERROR_CONVIRTIENDO_OBJ_A_JSON);
        }
    }
}
