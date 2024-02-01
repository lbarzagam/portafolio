package com.products.backend.persistence.mappers;

import com.products.backend.domain.entities.Product;
import com.products.backend.persistence.entities.ProductJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperJpa extends BaseJpaMapper<Product, ProductJpa> {
}
