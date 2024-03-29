package com.products.backend.persistence.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.products.backend.infra.util.json.GlobalJsonLocalDateTimeDeserializer;
import com.products.backend.infra.util.json.GlobalJsonLocalDateTimeUsingTimeZoneSerializer;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "category")
@Entity
public class CategoryJpa {
    @Id
    @Column(name = "id")
    private Integer id;
    @Type(type = "uuid-char")
    @Column(name = "unique_id")
    private UUID unique_id;
    private String name;
    private String image;
    @JsonDeserialize(using = GlobalJsonLocalDateTimeDeserializer.class)
    @JsonSerialize(using = GlobalJsonLocalDateTimeUsingTimeZoneSerializer.class)
    private LocalDateTime creationAt;
    @JsonDeserialize(using = GlobalJsonLocalDateTimeDeserializer.class)
    @JsonSerialize(using = GlobalJsonLocalDateTimeUsingTimeZoneSerializer.class)
    private LocalDateTime updatedAt;
}
