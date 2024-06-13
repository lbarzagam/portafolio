package com.microservicios.wastemanagerservice.mappers;

import com.microservicios.wastemanagerservice.dto.WasteCenterAuthorizationDto;
import com.microservicios.wastemanagerservice.dto.WasteManagerAddressDto;
import com.microservicios.wastemanagerservice.dto.WasteManagerDto;
import com.microservicios.wastemanagerservice.entities.WasteCenterAuthorizationEntity;
import com.microservicios.wastemanagerservice.entities.WasteManagerAddressEntity;
import com.microservicios.wastemanagerservice.entities.WasteManagerEntity;
import com.microservicios.wastemanagerservice.mappers.helpers.CycleAvoidingMappingContext;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_192 (Oracle Corporation)"
)
@Component
public class WasteManagerMapperImpl implements WasteManagerMapper {

    @Override
    public WasteManagerDto toDomainModel(WasteManagerEntity jpaModel, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        WasteManagerDto target = cycleAvoidingMappingContext.getMappedInstance( jpaModel, WasteManagerDto.class );
        if ( target != null ) {
            return target;
        }

        if ( jpaModel == null ) {
            return null;
        }

        WasteManagerDto wasteManagerDto = new WasteManagerDto();

        cycleAvoidingMappingContext.storeMappedInstance( jpaModel, wasteManagerDto );

        wasteManagerDto.setId( jpaModel.getId() );
        wasteManagerDto.setNombre( jpaModel.getNombre() );
        wasteManagerDto.setNif( jpaModel.getNif() );
        wasteManagerDto.setWasteManagerAddressEntity( wasteManagerAddressEntityToWasteManagerAddressDto( jpaModel.getWasteManagerAddressEntity(), cycleAvoidingMappingContext ) );
        wasteManagerDto.setListOfWasteCenterAuthorization( wasteCenterAuthorizationEntityListToWasteCenterAuthorizationDtoList( jpaModel.getListOfWasteCenterAuthorization(), cycleAvoidingMappingContext ) );
        wasteManagerDto.setIsEnabled( jpaModel.getIsEnabled() );
        wasteManagerDto.setVersion( jpaModel.getVersion() );
        wasteManagerDto.setCreatedDate( jpaModel.getCreatedDate() );
        wasteManagerDto.setLastModifiedDate( jpaModel.getLastModifiedDate() );

        return wasteManagerDto;
    }

    @Override
    public WasteManagerEntity toJpaModel(WasteManagerDto domainModel, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        WasteManagerEntity target = cycleAvoidingMappingContext.getMappedInstance( domainModel, WasteManagerEntity.class );
        if ( target != null ) {
            return target;
        }

        if ( domainModel == null ) {
            return null;
        }

        WasteManagerEntity wasteManagerEntity = new WasteManagerEntity();

        cycleAvoidingMappingContext.storeMappedInstance( domainModel, wasteManagerEntity );

        wasteManagerEntity.setId( domainModel.getId() );
        wasteManagerEntity.setNombre( domainModel.getNombre() );
        wasteManagerEntity.setNif( domainModel.getNif() );
        wasteManagerEntity.setWasteManagerAddressEntity( wasteManagerAddressDtoToWasteManagerAddressEntity( domainModel.getWasteManagerAddressEntity(), cycleAvoidingMappingContext ) );
        wasteManagerEntity.setListOfWasteCenterAuthorization( wasteCenterAuthorizationDtoListToWasteCenterAuthorizationEntityList( domainModel.getListOfWasteCenterAuthorization(), cycleAvoidingMappingContext ) );
        wasteManagerEntity.setIsEnabled( domainModel.getIsEnabled() );
        wasteManagerEntity.setVersion( domainModel.getVersion() );
        wasteManagerEntity.setCreatedDate( domainModel.getCreatedDate() );
        wasteManagerEntity.setLastModifiedDate( domainModel.getLastModifiedDate() );

        return wasteManagerEntity;
    }

    @Override
    public List<WasteManagerDto> toDomainModel(List<WasteManagerEntity> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<WasteManagerDto> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<WasteManagerDto> list1 = new ArrayList<WasteManagerDto>( list.size() );
        cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

        for ( WasteManagerEntity wasteManagerEntity : list ) {
            list1.add( toDomainModel( wasteManagerEntity, cycleAvoidingMappingContext ) );
        }

        return list1;
    }

    @Override
    public List<WasteManagerEntity> toJpaModel(List<WasteManagerDto> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<WasteManagerEntity> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<WasteManagerEntity> list1 = new ArrayList<WasteManagerEntity>( list.size() );
        cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

        for ( WasteManagerDto wasteManagerDto : list ) {
            list1.add( toJpaModel( wasteManagerDto, cycleAvoidingMappingContext ) );
        }

        return list1;
    }

    protected WasteManagerAddressDto wasteManagerAddressEntityToWasteManagerAddressDto(WasteManagerAddressEntity wasteManagerAddressEntity, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        WasteManagerAddressDto target = cycleAvoidingMappingContext.getMappedInstance( wasteManagerAddressEntity, WasteManagerAddressDto.class );
        if ( target != null ) {
            return target;
        }

        if ( wasteManagerAddressEntity == null ) {
            return null;
        }

        WasteManagerAddressDto wasteManagerAddressDto = new WasteManagerAddressDto();

        cycleAvoidingMappingContext.storeMappedInstance( wasteManagerAddressEntity, wasteManagerAddressDto );

        wasteManagerAddressDto.setId( wasteManagerAddressEntity.getId() );
        wasteManagerAddressDto.setDireccion( wasteManagerAddressEntity.getDireccion() );
        wasteManagerAddressDto.setIsEnabled( wasteManagerAddressEntity.getIsEnabled() );
        wasteManagerAddressDto.setVersion( wasteManagerAddressEntity.getVersion() );
        wasteManagerAddressDto.setCreatedDate( wasteManagerAddressEntity.getCreatedDate() );
        wasteManagerAddressDto.setLastModifiedDate( wasteManagerAddressEntity.getLastModifiedDate() );

        return wasteManagerAddressDto;
    }

    protected WasteCenterAuthorizationDto wasteCenterAuthorizationEntityToWasteCenterAuthorizationDto(WasteCenterAuthorizationEntity wasteCenterAuthorizationEntity, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        WasteCenterAuthorizationDto target = cycleAvoidingMappingContext.getMappedInstance( wasteCenterAuthorizationEntity, WasteCenterAuthorizationDto.class );
        if ( target != null ) {
            return target;
        }

        if ( wasteCenterAuthorizationEntity == null ) {
            return null;
        }

        WasteCenterAuthorizationDto wasteCenterAuthorizationDto = new WasteCenterAuthorizationDto();

        cycleAvoidingMappingContext.storeMappedInstance( wasteCenterAuthorizationEntity, wasteCenterAuthorizationDto );

        wasteCenterAuthorizationDto.setId( wasteCenterAuthorizationEntity.getId() );
        wasteCenterAuthorizationDto.setAuthorizationNumber( wasteCenterAuthorizationEntity.getAuthorizationNumber() );

        return wasteCenterAuthorizationDto;
    }

    protected List<WasteCenterAuthorizationDto> wasteCenterAuthorizationEntityListToWasteCenterAuthorizationDtoList(List<WasteCenterAuthorizationEntity> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<WasteCenterAuthorizationDto> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<WasteCenterAuthorizationDto> list1 = new ArrayList<WasteCenterAuthorizationDto>( list.size() );
        cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

        for ( WasteCenterAuthorizationEntity wasteCenterAuthorizationEntity : list ) {
            list1.add( wasteCenterAuthorizationEntityToWasteCenterAuthorizationDto( wasteCenterAuthorizationEntity, cycleAvoidingMappingContext ) );
        }

        return list1;
    }

    protected WasteManagerAddressEntity wasteManagerAddressDtoToWasteManagerAddressEntity(WasteManagerAddressDto wasteManagerAddressDto, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        WasteManagerAddressEntity target = cycleAvoidingMappingContext.getMappedInstance( wasteManagerAddressDto, WasteManagerAddressEntity.class );
        if ( target != null ) {
            return target;
        }

        if ( wasteManagerAddressDto == null ) {
            return null;
        }

        WasteManagerAddressEntity wasteManagerAddressEntity = new WasteManagerAddressEntity();

        cycleAvoidingMappingContext.storeMappedInstance( wasteManagerAddressDto, wasteManagerAddressEntity );

        wasteManagerAddressEntity.setId( wasteManagerAddressDto.getId() );
        wasteManagerAddressEntity.setDireccion( wasteManagerAddressDto.getDireccion() );
        wasteManagerAddressEntity.setIsEnabled( wasteManagerAddressDto.getIsEnabled() );
        wasteManagerAddressEntity.setVersion( wasteManagerAddressDto.getVersion() );
        wasteManagerAddressEntity.setCreatedDate( wasteManagerAddressDto.getCreatedDate() );
        wasteManagerAddressEntity.setLastModifiedDate( wasteManagerAddressDto.getLastModifiedDate() );

        return wasteManagerAddressEntity;
    }

    protected WasteCenterAuthorizationEntity wasteCenterAuthorizationDtoToWasteCenterAuthorizationEntity(WasteCenterAuthorizationDto wasteCenterAuthorizationDto, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        WasteCenterAuthorizationEntity target = cycleAvoidingMappingContext.getMappedInstance( wasteCenterAuthorizationDto, WasteCenterAuthorizationEntity.class );
        if ( target != null ) {
            return target;
        }

        if ( wasteCenterAuthorizationDto == null ) {
            return null;
        }

        WasteCenterAuthorizationEntity wasteCenterAuthorizationEntity = new WasteCenterAuthorizationEntity();

        cycleAvoidingMappingContext.storeMappedInstance( wasteCenterAuthorizationDto, wasteCenterAuthorizationEntity );

        wasteCenterAuthorizationEntity.setId( wasteCenterAuthorizationDto.getId() );
        wasteCenterAuthorizationEntity.setAuthorizationNumber( wasteCenterAuthorizationDto.getAuthorizationNumber() );

        return wasteCenterAuthorizationEntity;
    }

    protected List<WasteCenterAuthorizationEntity> wasteCenterAuthorizationDtoListToWasteCenterAuthorizationEntityList(List<WasteCenterAuthorizationDto> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<WasteCenterAuthorizationEntity> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<WasteCenterAuthorizationEntity> list1 = new ArrayList<WasteCenterAuthorizationEntity>( list.size() );
        cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

        for ( WasteCenterAuthorizationDto wasteCenterAuthorizationDto : list ) {
            list1.add( wasteCenterAuthorizationDtoToWasteCenterAuthorizationEntity( wasteCenterAuthorizationDto, cycleAvoidingMappingContext ) );
        }

        return list1;
    }
}
