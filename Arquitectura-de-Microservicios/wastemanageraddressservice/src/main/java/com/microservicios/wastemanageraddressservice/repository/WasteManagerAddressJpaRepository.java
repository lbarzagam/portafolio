package com.microservicios.wastemanageraddressservice.repository;

import com.microservicios.wastemanageraddressservice.entities.WasteManagerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteManagerAddressJpaRepository extends JpaRepository<WasteManagerAddressEntity, Long>, JpaSpecificationExecutor<WasteManagerAddressEntity> {
}
