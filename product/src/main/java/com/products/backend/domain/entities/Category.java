package com.products.backend.domain.entities;

import com.products.backend.infra.util.json.GlobalJsonLocalDateTimeDeserializer;
import com.products.backend.infra.util.json.GlobalJsonLocalDateTimeUsingTimeZoneSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Category {
    private UUID unique_id;
    private Integer id;
    private String name;
    private String image;
    @JsonDeserialize(using = GlobalJsonLocalDateTimeDeserializer.class)
    @JsonSerialize(using = GlobalJsonLocalDateTimeUsingTimeZoneSerializer.class)
    private LocalDateTime creationAt;
    @JsonDeserialize(using = GlobalJsonLocalDateTimeDeserializer.class)
    @JsonSerialize(using = GlobalJsonLocalDateTimeUsingTimeZoneSerializer.class)
    private LocalDateTime updatedAt;
}
