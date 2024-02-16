package com.products.backend.persistence.mappers;

import com.products.backend.domain.entities.Category;
import com.products.backend.persistence.entities.CategoryJpa;
import com.products.backend.persistence.mappers.helpers.CycleAvoidingMappingContext;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_192 (Oracle Corporation)"
)
@Component
public class CategoryMapperJpaImpl implements CategoryMapperJpa {

    @Override
    public Category toDomainModel(CategoryJpa jpaModel, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        Category target = cycleAvoidingMappingContext.getMappedInstance( jpaModel, Category.class );
        if ( target != null ) {
            return target;
        }

        if ( jpaModel == null ) {
            return null;
        }

        Category category = new Category();

        cycleAvoidingMappingContext.storeMappedInstance( jpaModel, category );

        category.setUnique_id( jpaModel.getUnique_id() );
        category.setId( jpaModel.getId() );
        category.setName( jpaModel.getName() );
        category.setImage( jpaModel.getImage() );
        category.setCreationAt( jpaModel.getCreationAt() );
        category.setUpdatedAt( jpaModel.getUpdatedAt() );

        return category;
    }

    @Override
    public CategoryJpa toJpaModel(Category domainModel, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        CategoryJpa target = cycleAvoidingMappingContext.getMappedInstance( domainModel, CategoryJpa.class );
        if ( target != null ) {
            return target;
        }

        if ( domainModel == null ) {
            return null;
        }

        CategoryJpa categoryJpa = new CategoryJpa();

        cycleAvoidingMappingContext.storeMappedInstance( domainModel, categoryJpa );

        categoryJpa.setId( domainModel.getId() );
        categoryJpa.setUnique_id( domainModel.getUnique_id() );
        categoryJpa.setName( domainModel.getName() );
        categoryJpa.setImage( domainModel.getImage() );
        categoryJpa.setCreationAt( domainModel.getCreationAt() );
        categoryJpa.setUpdatedAt( domainModel.getUpdatedAt() );

        return categoryJpa;
    }

    @Override
    public List<Category> toDomainModel(List<CategoryJpa> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<Category> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<Category> list1 = new ArrayList<Category>( list.size() );
        cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

        for ( CategoryJpa categoryJpa : list ) {
            list1.add( toDomainModel( categoryJpa, cycleAvoidingMappingContext ) );
        }

        return list1;
    }

    @Override
    public List<CategoryJpa> toJpaModel(List<Category> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<CategoryJpa> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<CategoryJpa> list1 = new ArrayList<CategoryJpa>( list.size() );
        cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

        for ( Category category : list ) {
            list1.add( toJpaModel( category, cycleAvoidingMappingContext ) );
        }

        return list1;
    }
}
