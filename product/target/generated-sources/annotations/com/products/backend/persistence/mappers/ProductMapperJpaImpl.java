package com.products.backend.persistence.mappers;

import com.products.backend.domain.entities.Category;
import com.products.backend.domain.entities.Product;
import com.products.backend.persistence.entities.CategoryJpa;
import com.products.backend.persistence.entities.ProductJpa;
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
public class ProductMapperJpaImpl implements ProductMapperJpa {

    @Override
    public Product toDomainModel(ProductJpa jpaModel, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        Product target = cycleAvoidingMappingContext.getMappedInstance( jpaModel, Product.class );
        if ( target != null ) {
            return target;
        }

        if ( jpaModel == null ) {
            return null;
        }

        Product product = new Product();

        cycleAvoidingMappingContext.storeMappedInstance( jpaModel, product );

        product.setUnique_id( jpaModel.getUnique_id() );
        product.setId( jpaModel.getId() );
        product.setTitle( jpaModel.getTitle() );
        product.setCreationAt( jpaModel.getCreationAt() );
        product.setUpdatedAt( jpaModel.getUpdatedAt() );
        product.setPrice( jpaModel.getPrice() );
        product.setDescription( jpaModel.getDescription() );
        List<String> list = jpaModel.getImages();
        if ( list != null ) {
            product.setImages( new ArrayList<String>( list ) );
        }
        product.setCategory( categoryJpaToCategory( jpaModel.getCategory(), cycleAvoidingMappingContext ) );

        return product;
    }

    @Override
    public ProductJpa toJpaModel(Product domainModel, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        ProductJpa target = cycleAvoidingMappingContext.getMappedInstance( domainModel, ProductJpa.class );
        if ( target != null ) {
            return target;
        }

        if ( domainModel == null ) {
            return null;
        }

        ProductJpa productJpa = new ProductJpa();

        cycleAvoidingMappingContext.storeMappedInstance( domainModel, productJpa );

        productJpa.setUnique_id( domainModel.getUnique_id() );
        productJpa.setId( domainModel.getId() );
        productJpa.setTitle( domainModel.getTitle() );
        productJpa.setCreationAt( domainModel.getCreationAt() );
        productJpa.setUpdatedAt( domainModel.getUpdatedAt() );
        productJpa.setPrice( domainModel.getPrice() );
        productJpa.setDescription( domainModel.getDescription() );
        List<String> list = domainModel.getImages();
        if ( list != null ) {
            productJpa.setImages( new ArrayList<String>( list ) );
        }
        productJpa.setCategory( categoryToCategoryJpa( domainModel.getCategory(), cycleAvoidingMappingContext ) );

        return productJpa;
    }

    @Override
    public List<Product> toDomainModel(List<ProductJpa> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<Product> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

        for ( ProductJpa productJpa : list ) {
            list1.add( toDomainModel( productJpa, cycleAvoidingMappingContext ) );
        }

        return list1;
    }

    @Override
    public List<ProductJpa> toJpaModel(List<Product> list, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        List<ProductJpa> target = cycleAvoidingMappingContext.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<ProductJpa> list1 = new ArrayList<ProductJpa>( list.size() );
        cycleAvoidingMappingContext.storeMappedInstance( list, list1 );

        for ( Product product : list ) {
            list1.add( toJpaModel( product, cycleAvoidingMappingContext ) );
        }

        return list1;
    }

    protected Category categoryJpaToCategory(CategoryJpa categoryJpa, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        Category target = cycleAvoidingMappingContext.getMappedInstance( categoryJpa, Category.class );
        if ( target != null ) {
            return target;
        }

        if ( categoryJpa == null ) {
            return null;
        }

        Category category = new Category();

        cycleAvoidingMappingContext.storeMappedInstance( categoryJpa, category );

        category.setId( categoryJpa.getId() );
        category.setName( categoryJpa.getName() );
        category.setImage( categoryJpa.getImage() );
        category.setCreationAt( categoryJpa.getCreationAt() );
        category.setUpdatedAt( categoryJpa.getUpdatedAt() );

        return category;
    }

    protected CategoryJpa categoryToCategoryJpa(Category category, CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        CategoryJpa target = cycleAvoidingMappingContext.getMappedInstance( category, CategoryJpa.class );
        if ( target != null ) {
            return target;
        }

        if ( category == null ) {
            return null;
        }

        CategoryJpa categoryJpa = new CategoryJpa();

        cycleAvoidingMappingContext.storeMappedInstance( category, categoryJpa );

        categoryJpa.setId( category.getId() );
        categoryJpa.setName( category.getName() );
        categoryJpa.setImage( category.getImage() );
        categoryJpa.setCreationAt( category.getCreationAt() );
        categoryJpa.setUpdatedAt( category.getUpdatedAt() );

        return categoryJpa;
    }
}
