package com.categories.backend.domain.services;

import com.categories.backend.domain.entities.Category;
import com.categories.backend.infra.util.specification.BaseSpecification;
import com.categories.backend.persistence.mappers.CategoryMapperJpa;
import com.categories.backend.persistence.repositories.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryMapperJpa categoryMapperJpa;

    public void getListCategoryFromCola(List<Map<String, Object>> message_map) {
        List<Category>categoryList = new ArrayList<>();
        List<String>categoryName = new ArrayList<>();

        for (Map mp: message_map){
            Category category = new Category();
            category.categoryFromMap(mp);
            categoryList.add(category);
            categoryName.add(category.getName());
        }

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
                    existe.set(false);
                }
            }
            if(!categoryToSave.isEmpty())
                categoryJpaRepository.saveAll(categoryMapperJpa.toJpaModel(categoryToSave));
        }
    }
}
