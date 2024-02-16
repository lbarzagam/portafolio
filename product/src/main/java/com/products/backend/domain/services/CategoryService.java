package com.products.backend.domain.services;

import com.products.backend.infra.services.AmqpPublisherService;
import com.products.backend.infra.util.specification.BaseSpecification;
import com.products.backend.domain.entities.Category;
import com.products.backend.persistence.mappers.CategoryMapperJpa;
import com.products.backend.persistence.repositories.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryMapperJpa categoryMapperJpa;
    private final AmqpPublisherService amqpPublisherService;

    public void registrarCategorias(List<Category> categoryList) throws Exception {

        List<String>categoryName = new ArrayList<>();
        categoryList.forEach(category -> {
            categoryName.add(category.getName());
        });

        List<Category>categoryListBD;
        List<Category>categoryToSave = new ArrayList<>();
        if(!CollectionUtils.isEmpty(categoryName)) {
            Specification spec = BaseSpecification.empty();
            spec = spec.and(BaseSpecification.fieldIn("name", categoryName));
            categoryListBD = categoryMapperJpa.toDomainModel(categoryJpaRepository.findAll(spec));

            AtomicBoolean existe = new AtomicBoolean(false);
            for (Category category : categoryList) {
                categoryListBD.forEach(cat -> {
                    if (cat.getName().equals(category.getName())) {
                        existe.set(true);
                    }
                });
                if (!existe.get()) {
                    category.setUnique_id(UUID.randomUUID());
                    categoryToSave.add(category);
                }
                existe.set(false);
            }
            if(!categoryToSave.isEmpty()) {
                categoryJpaRepository.saveAll(categoryMapperJpa.toJpaModel(categoryToSave));
                amqpPublisherService.publishMessage(categoryList);
            }
        }
    }
}
