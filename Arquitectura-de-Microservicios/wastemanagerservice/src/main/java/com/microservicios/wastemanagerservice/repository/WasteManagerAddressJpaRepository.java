package com.microservicios.wastemanagerservice.repository;

import com.microservicios.wastemanagerservice.entities.WasteManagerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteManagerAddressJpaRepository extends JpaRepository<WasteManagerAddressEntity, Long> {
}
