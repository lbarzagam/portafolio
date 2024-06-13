package com.microservicios.wastemanagerservice.service;

import com.microservicios.wastemanagerservice.entities.WasteManagerEntity;
import com.microservicios.wastemanagerservice.repository.WasteManagerJpaRepository;
import com.microservicios.wastemanagerservice.client.WasteManagerAddressClient;
import com.microservicios.wastemanagerservice.dto.WasteManagerDto;
import com.microservicios.wastemanagerservice.dto.WasteManagerDtoResponse;
import com.microservicios.wastemanagerservice.infra.exceptions.DomainException;
import com.microservicios.wastemanagerservice.infra.util.reflection.Util_Reflection;
import com.microservicios.wastemanagerservice.infra.validators.CodigoErrorEnum;
import com.microservicios.wastemanagerservice.mappers.WasteManagerMapper;
import lombok.RequiredArgsConstructor;
import org.boon.core.reflection.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WasteManagerService {
    private final WasteManagerJpaRepository wasteManagerJpaRepository;
    private final WasteManagerMapper wasteManagerMapper;
    private final WasteManagerAddressClient wasteManagerAddressClient;

    public ResponseEntity sayHello() {
        return new ResponseEntity("Hello", HttpStatus.OK);
    }

    public List<WasteManagerDtoResponse> listWaste() {
        List<WasteManagerDtoResponse> wasteManagerDtoResponseList = new ArrayList<>();
        wasteManagerJpaRepository.findAll().iterator().forEachRemaining(wasteManagerEntity -> {

            WasteManagerDtoResponse wasteManagerDtoResponse = new WasteManagerDtoResponse();
            wasteManagerDtoResponse.crearNuevo(wasteManagerMapper.toDomainModel(wasteManagerEntity));

            wasteManagerDtoResponseList.add(wasteManagerDtoResponse);
        });
        return wasteManagerDtoResponseList;
    }

    public WasteManagerDto crearWaste(WasteManagerDto wasteManagerDto) {
        WasteManagerEntity wasteManagerEntity = wasteManagerMapper.toJpaModel(wasteManagerDto);
        return wasteManagerMapper.toDomainModel(wasteManagerJpaRepository.save(wasteManagerEntity));
    }

    public WasteManagerDto updateWaste(WasteManagerDto wasteManagerDto) {
        WasteManagerEntity wasteEntityBD = wasteManagerMapper.toJpaModel(findWasteById(wasteManagerDto.getId()));
        BeanUtils.copyProperties(wasteManagerDto, wasteEntityBD, Util_Reflection.getNullPropertyNames(wasteManagerDto));
        return wasteManagerMapper.toDomainModel(wasteManagerJpaRepository.save(wasteEntityBD));
    }

    public WasteManagerDto findWasteById(Long id) {
        Optional<WasteManagerEntity> wasteManagerEntityOptional = wasteManagerJpaRepository.getWasteManagerEntitiesById(id);
        if (!wasteManagerEntityOptional.isPresent()) {
            throw new DomainException(CodigoErrorEnum.ERROR_INTEGRIDAD_DATOS,
                    String.format("La entidad WasteManagerEntity con id: %s no fue encontrada", id.toString()));
        }
        return wasteManagerMapper.toDomainModel(wasteManagerEntityOptional.get());
    }
}
