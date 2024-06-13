package com.products.backend.persistence.repositories;

import com.products.backend.persistence.entities.CategoryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryJpa, UUID>, JpaSpecificationExecutor<CategoryJpa> {
}
