package com.microservicios.wastemanagerservice.mappers;

import com.microservicios.wastemanagerservice.entities.WasteManagerAddressEntity;
import com.microservicios.wastemanagerservice.dto.WasteManagerAddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WasteManagerAddressMapper extends BaseJpaMapper<WasteManagerAddressDto, WasteManagerAddressEntity>{
}
