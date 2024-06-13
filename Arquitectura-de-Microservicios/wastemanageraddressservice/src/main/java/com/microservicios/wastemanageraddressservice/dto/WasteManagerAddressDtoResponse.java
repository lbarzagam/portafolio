package com.microservicios.wastemanageraddressservice.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

@Data
public class WasteManagerAddressDtoResponse {
    private String direccion;
    private Boolean isEnabled = Boolean.TRUE;
    private Long version = 0L;
    private Date createdDate;

    public void crearNuevo(WasteManagerAddressDto wasteManagerAddressDto) {
        this.direccion = StringUtils.isNotEmpty(wasteManagerAddressDto.getDireccion()) ? wasteManagerAddressDto.getDireccion() : "";
        this.isEnabled = Objects.nonNull(wasteManagerAddressDto.getIsEnabled()) ? wasteManagerAddressDto.getIsEnabled() : null;
        this.version = Objects.nonNull(wasteManagerAddressDto.getVersion()) ? wasteManagerAddressDto.getVersion() : null;
        this.createdDate = Objects.nonNull(wasteManagerAddressDto.getCreatedDate()) ? wasteManagerAddressDto.getCreatedDate() : null;
    }
}

