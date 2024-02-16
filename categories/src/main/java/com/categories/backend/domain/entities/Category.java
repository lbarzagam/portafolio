package com.categories.backend.domain.entities;

import com.categories.backend.infra.util.datetime.Util_DateTime;
import com.categories.backend.infra.util.json.GlobalJsonLocalDateTimeDeserializer;
import com.categories.backend.infra.util.json.GlobalJsonLocalDateTimeUsingTimeZoneSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
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

    public void categoryFromMap(Map<String, Object> mapCategory){
        this.id=Integer.parseInt(mapCategory.get("id").toString());
        this.name=mapCategory.get("name").toString();
        this.image=mapCategory.get("image").toString();
        this.creationAt= Util_DateTime.deserializarFechaHora(mapCategory.get("creationAt").toString(), true);
        this.updatedAt= Util_DateTime.deserializarFechaHora(mapCategory.get("updatedAt").toString(), true);
    }
}