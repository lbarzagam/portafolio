package com.microservicios.wastemanageraddressservice.mappers;

import com.microservicios.wastemanageraddressservice.dto.WasteManagerAddressDto;
import com.microservicios.wastemanageraddressservice.entities.WasteManagerAddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WasteManagerAddressMapper extends BaseJpaMapper<WasteManagerAddressDto, WasteManagerAddressEntity> {
}
