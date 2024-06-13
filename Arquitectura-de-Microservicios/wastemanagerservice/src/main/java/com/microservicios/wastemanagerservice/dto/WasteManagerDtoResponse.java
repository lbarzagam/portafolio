package com.microservicios.wastemanagerservice.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class WasteManagerDtoResponse {
    private String nombre;
    private String nif;
    private Boolean isEnabled;
    private Long version;
    private Date createdDate;
    private Date lastModifiedDate;
    private String managerAddress;
    private String centerAuthorization;

    public void crearNuevo(WasteManagerDto wasteManagerDto) {
        this.nombre = StringUtils.isNotEmpty(wasteManagerDto.getNombre()) ? wasteManagerDto.getNombre() : "" ;
        this.nif = StringUtils.isNotEmpty(wasteManagerDto.getNif()) ? wasteManagerDto.getNif() : "" ;
        this.isEnabled = Objects.nonNull(wasteManagerDto.getIsEnabled()) ? wasteManagerDto.getIsEnabled() : null;
        this.version = Objects.nonNull(wasteManagerDto.getVersion()) ? wasteManagerDto.getVersion() : null;
        this.createdDate = Objects.nonNull(wasteManagerDto.getCreatedDate()) ? wasteManagerDto.getCreatedDate() : null;
        this.lastModifiedDate = Objects.nonNull(wasteManagerDto.getLastModifiedDate()) ? wasteManagerDto.getLastModifiedDate() : null;
        this.managerAddress = Objects.nonNull(wasteManagerDto.getWasteManagerAddressEntity()) ? wasteManagerDto.getWasteManagerAddressEntity().getDireccion() : "";
        this.centerAuthorization = wasteManagerDto.getListOfWasteCenterAuthorization().stream().map(WasteCenterAuthorizationDto::getAuthorizationNumber).collect(Collectors.joining(","));
    }
}
