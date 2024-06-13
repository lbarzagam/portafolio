package com.microservicios.wastemanagerservice.mappers;

import com.microservicios.wastemanagerservice.entities.WasteManagerEntity;
import com.microservicios.wastemanagerservice.dto.WasteManagerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WasteManagerMapper extends BaseJpaMapper<WasteManagerDto, WasteManagerEntity>{
}
