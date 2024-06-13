package com.microservicios.wastemanagerservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "manager_address")
@Entity
public class WasteManagerAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direccion;
    private Boolean isEnabled;
    private Long version;
    private Date createdDate;
    private Date lastModifiedDate;
}
