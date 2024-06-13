package com.products.backend.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.products.backend.domain.entities.Category;
import com.products.backend.infra.util.json.GlobalJsonLocalDateTimeDeserializer;
import com.products.backend.infra.util.json.GlobalJsonLocalDateTimeUsingTimeZoneSerializer;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "product")
@Entity
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class ProductJpa {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "unique_id")
    private UUID unique_id;
    private Integer id;
    private String title;
    @JsonDeserialize(using = GlobalJsonLocalDateTimeDeserializer.class)
    @JsonSerialize(using = GlobalJsonLocalDateTimeUsingTimeZoneSerializer.class)
    private LocalDateTime creationAt;
    @JsonDeserialize(using = GlobalJsonLocalDateTimeDeserializer.class)
    @JsonSerialize(using = GlobalJsonLocalDateTimeUsingTimeZoneSerializer.class)
    private LocalDateTime updatedAt;
    private Double price;
    private String description;
    @Type(type = "list-array")
    private List<String> images;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryJpa category;
}
