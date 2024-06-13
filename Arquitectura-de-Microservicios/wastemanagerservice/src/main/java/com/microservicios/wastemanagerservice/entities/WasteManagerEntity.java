package com.microservicios.wastemanagerservice.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "manager")
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class WasteManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String nif;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_address_id", referencedColumnName = "id")
    private WasteManagerAddressEntity wasteManagerAddressEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private List<WasteCenterAuthorizationEntity> listOfWasteCenterAuthorization;

    private Boolean isEnabled;
    private Long version;
    private Date createdDate;
    private Date lastModifiedDate;
}
