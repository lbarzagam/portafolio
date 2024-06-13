package com.microservicios.wastemanagerservice.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class WasteManagerDto {
    private Long id;
    private String nombre;
    private String nif;
    private WasteManagerAddressDto wasteManagerAddressEntity;
    private List<WasteCenterAuthorizationDto> listOfWasteCenterAuthorization = new ArrayList<>();
    private Boolean isEnabled = Boolean.TRUE;
    private Long version = 0L;
    private Date createdDate;
    private Date lastModifiedDate;
}
