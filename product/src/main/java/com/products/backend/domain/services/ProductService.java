package com.products.backend.domain.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.backend.domain.entities.Product;
import com.products.backend.infra.http.HttpClient;
import com.products.backend.persistence.entities.ProductJpa;
import com.products.backend.persistence.mappers.ProductMapperJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class ProductService {

    private final HttpClient httpClient;
    private final ProductMapperJpa productMapperJpa;

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
        List<ProductJpa> productJpaList = productMapperJpa.toJpaModel(response);

        return response;
    }

    public void registrarProductos() {

    }
}
