package com.categories.backend.persistence.mappers;

import com.categories.backend.domain.entities.Category;
import com.categories.backend.persistence.entities.CategoryJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapperJpa extends BaseJpaMapper<Category, CategoryJpa> {
}
