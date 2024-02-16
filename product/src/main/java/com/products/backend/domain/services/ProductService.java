package com.products.backend.domain.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.backend.domain.entities.Category;
import com.products.backend.domain.entities.Product;
import com.products.backend.infra.http.HttpClient;
import com.products.backend.infra.services.AmqpPublisherService;
import com.products.backend.infra.util.datetime.Util_DateTime;
import com.products.backend.infra.util.specification.BaseSpecification;
import com.products.backend.persistence.mappers.ProductMapperJpa;
import com.products.backend.persistence.repositories.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Component
@RequiredArgsConstructor
public class ProductService {

    private final HttpClient httpClient;
    private final ProductMapperJpa productMapperJpa;
    private final ProductJpaRepository productJpaRepository;

    private final CategoryService categoryService;

    @Value("${apiUrl}")
    private String apiUrl;

    public  List<Product> getProductToRegister(String ticket) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> response = httpClient.executeGet(ticket, "Get Product List", apiUrl , null, List.class, true);
        response = objectMapper.convertValue(response, new TypeReference<List<Product>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        this.registroCategorias(response);
        this.registrarProductos(response);
        return response;
    }

    @Async
    public void registroCategorias(List<Product> productList) throws Exception {
        //Filtrar lista de categorias y enviar Categorias a Cola
        Set<Category> categoriesList = new HashSet<>();
        productList.forEach(product -> {
            categoriesList.add(product.getCategory());
        });
        categoryService.registrarCategorias(categoriesList.parallelStream().collect(Collectors.toList()));
    }

    @Transactional
    public void registrarProductos(List<Product> productList) {

        List<Product> productMenorPrecioResult = new ArrayList<>();
        List<String> productStrId = new ArrayList<>();
        for (Product product : productList) {
            if (product.getPrice() < 20)
                productMenorPrecioResult.add(product);
        }

        productMenorPrecioResult.forEach(product -> {
            productStrId.add(product.getId().toString());
        });

        List<Product> productResultBd = new ArrayList<>();
        if(!CollectionUtils.isEmpty(productStrId)) {
            Specification spec = BaseSpecification.empty();
            spec.and(BaseSpecification.fieldIn("id", productStrId));
            productResultBd = productMapperJpa.toDomainModel(productJpaRepository.findAll(spec));
        }

        AtomicBoolean existe = new AtomicBoolean(false);
        List<Product> productsToSave = new ArrayList<>();
        for (Product product: productMenorPrecioResult) {
            productResultBd.forEach(prod -> {
                if (product.getId().equals(prod.getId()) && product.getTitle().equals(prod.getTitle())) {
                    existe.set(true);
                }
            });
            if(!existe.get()){
                product.setUnique_id(UUID.randomUUID());
                productsToSave.add(product);
                existe.set(false);
            }
        }
        productJpaRepository.saveAll(productMapperJpa.toJpaModel(productsToSave));
    }

    public List<Product> getProductList(Integer id, String title, String creationAt, Double price, String description, String categoryName){
        Specification spec = BaseSpecification.empty();
        if(id !=null && id > 0)
            spec.and(BaseSpecification.fieldEquals("id", id));
        if(StringUtils.isNotEmpty(title))
            spec.and(BaseSpecification.fieldContainsIgnoreCase("title", title));
        if(StringUtils.isNotEmpty(creationAt)) {
            LocalDateTime createdAt = Util_DateTime.deserializarFechaHora(creationAt, false);
            spec.and(BaseSpecification.fieldEquals("creationAt", createdAt));
        }
        if(price!=null && price > 0)
            spec.and(BaseSpecification.fieldEquals("price", price));
        if(StringUtils.isNotEmpty(description))
            spec.and(BaseSpecification.fieldContainsIgnoreCase("description", description));
        if(StringUtils.isNotEmpty(categoryName))
            spec.and(BaseSpecification.fieldContainsIgnoreCase("categoria.name", categoryName));


        return productMapperJpa.toDomainModel(productJpaRepository.findAll(spec));
    }



}
