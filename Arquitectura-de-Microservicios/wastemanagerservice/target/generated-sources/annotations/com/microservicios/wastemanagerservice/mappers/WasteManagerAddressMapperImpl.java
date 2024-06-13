package com.microservicios.wastemanagerservice.mappers;

import com.microservicios.wastemanagerservice.dto.WasteManagerAddressDto;
import com.microservicios.wastemanagerservice.entities.WasteManagerAddressEntity;
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
public class WasteManagerAddressMapperImpl implements WasteManagerAddressMapper {

    @Override
    public WasteManagerAddressDto toDomainModel(WasteManagerAddressEntity jpaModel, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        WasteManagerAddressDto target = cycleAvoidingMappingContext.getMappedInstance( jpaModel, WasteManagerAddressDto.class );
        if ( target != null ) {
            return target;
        }

        if ( jpaModel == null ) {
            return null;
        }

        WasteManagerAddressDto wasteManagerAddressDto = new WasteManagerAddressDto();

        cycleAvoidingMappingContext.storeMappedInstance( jpaModel, wasteManagerAddressDto );

        wasteManagerAddressDto.setId( jpaModel.getId() );
        wasteManagerAddressDto.setDireccion( jpaModel.getDireccion() );
        wasteManagerAddressDto.setIsEnabled( jpaModel.getIsEnabled() );
        wasteManagerAddressDto.setVersion( jpaModel.getVersion() );
        wasteManagerAddressDto.setCreatedDate( jpaModel.getCreatedDate() );
        wasteManagerAddressDto.setLastModifiedDate( jpaModel.getLastModifiedDate() );

        return wasteManagerAddressDto;
    }

    @Override
    public WasteManagerAddressEntity toJpaModel(WasteManagerAddressDto domainModel, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        WasteManagerAddressEntity target = cycleAvoidingMappingContext.getMappedInstance( domainModel, WasteManagerAddressEntity.class );
        if ( target != null ) {
            return target;
        }

        if ( domainModel == null ) {
            return null;
        }

        WasteManagerAddressEntity wasteManagerAddressEntity = new WasteManagerAddressEntity();

        cycleAvoidingMappingContext.storeMappedInstance( domainModel, wasteManagerAddressEntity );

        wasteManagerAddressEntity.setId( domainModel.getId() );
        wasteManagerAddressEntity.setDireccion( domainModel.getDireccion() );
        wasteManagerAddressEntity.setIsEnabled( domainModel.getIsEnabled() );
        wasteManagerAddressEntity.setVersion( domainModel.getVersion() );
        wasteManagerAddressEntity.setCreatedDate( domainModel.getCreatedDate() );
        wasteManagerAddressEntity.setLastModifiedDate( domainModel.getLastModifiedDate() );

        return wasteManagerAddressEntity;
    }

    @Override
    public List<WasteManagerAddressDto> toDomainModel(List<WasteManagerAddressEntity> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<WasteManagerAddressDto> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<WasteManagerAddressDto> list1 = new ArrayList<WasteManagerAddressDto>( list.size() );
        cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

        for ( WasteManagerAddressEntity wasteManagerAddressEntity : list ) {
            list1.add( toDomainModel( wasteManagerAddressEntity, cycleAvoidingMappingContext ) );
        }

        return list1;
    }

    @Override
    public List<WasteManagerAddressEntity> toJpaModel(List<WasteManagerAddressDto> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<WasteManagerAddressEntity> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<WasteManagerAddressEntity> list1 = new ArrayList<WasteManagerAddressEntity>( list.size() );
        cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

        for ( WasteManagerAddressDto wasteManagerAddressDto : list ) {
            list1.add( toJpaModel( wasteManagerAddressDto, cycleAvoidingMappingContext ) );
        }

        return list1;
    }
}
