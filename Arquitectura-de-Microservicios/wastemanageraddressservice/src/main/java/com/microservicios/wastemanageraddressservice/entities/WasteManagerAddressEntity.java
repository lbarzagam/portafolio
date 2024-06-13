package com.microservicios.wastemanageraddressservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "manager_address")
@Entity
public class WasteManagerAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String direccion;
    private Boolean isEnabled = Boolean.TRUE;
    private Long version = 0L;
    private Date createdDate;
    private Date lastModifiedDate;
}
