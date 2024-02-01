package com.products.backend.controllers;

import com.products.backend.domain.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
@Api(tags = "Productos")
public class ProductRestContoller {

    private final ProductService productService;

    @ApiOperation(value = "Servicio que devuelve el resumen para el detalle de una liquidación")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liquidación recuperada satisfactoriamente"),
            @ApiResponse(code = 500, message = "Error interno")})
    @GetMapping("/list")
    public ResponseEntity<?> getProducts() throws Exception {
        return new ResponseEntity<>(productService.getProductList(UUID.randomUUID().toString()), HttpStatus.OK);
    }
}
