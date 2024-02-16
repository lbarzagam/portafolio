package com.products.backend.domain.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.backend.domain.entities.Category;
import com.products.backend.domain.entities.Product;
import com.products.backend.infra.http.HttpClient;
import com.products.backend.infra.services.AmqpPublisherService;
import com.products.backend.infra.util.specification.BaseSpecification;
import com.products.backend.persistence.mappers.ProductMapperJpa;
import com.products.backend.persistence.repositories.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
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

    public  List<Product> getProductList(String ticket) throws Exception {
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


}
