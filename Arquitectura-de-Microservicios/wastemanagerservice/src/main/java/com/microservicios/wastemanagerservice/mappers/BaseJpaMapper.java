package com.microservicios.wastemanagerservice.mappers;

import com.microservicios.wastemanagerservice.mappers.helpers.CycleAvoidingMappingContext;
import com.microservicios.wastemanagerservice.mappers.helpers.DoIgnore;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseJpaMapper<D, JPA> {

    D toDomainModel(JPA jpaModel, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default D toDomainModel(JPA jpaModel) {
        return toDomainModel(jpaModel, new CycleAvoidingMappingContext());
    }

    @InheritInverseConfiguration
    JPA toJpaModel(D domainModel, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default JPA toJpaModel(D domainModel) {
        return toJpaModel(domainModel, new CycleAvoidingMappingContext());
    }

    List<D> toDomainModel(List<JPA> list, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default List<D> toDomainModel(List<JPA> list) {
        return toDomainModel(list, new CycleAvoidingMappingContext());
    }

    @InheritInverseConfiguration
    List<JPA> toJpaModel(List<D> list, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default List<JPA> toJpaModel(List<D> list) {
        return toJpaModel(list, new CycleAvoidingMappingContext());
    }

    default Page<D> toDomainModel(Page<JPA> page) {
        return page.map(entity -> toDomainModel(entity, new CycleAvoidingMappingContext()));
    }
}
