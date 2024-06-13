package com.products.backend.persistence.mappers;

import com.products.backend.domain.entities.Category;
import com.products.backend.persistence.entities.CategoryJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapperJpa extends BaseJpaMapper<Category, CategoryJpa> {
}
