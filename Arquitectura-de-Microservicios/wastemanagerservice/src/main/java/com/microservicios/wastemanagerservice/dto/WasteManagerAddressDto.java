package com.microservicios.wastemanagerservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WasteManagerAddressDto {
    private Long id;
    private String direccion;
    private Boolean isEnabled = Boolean.TRUE;
    private Long version = 0L;
    private Date createdDate;
    private Date lastModifiedDate;
}
