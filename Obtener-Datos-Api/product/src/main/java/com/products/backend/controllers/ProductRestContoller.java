package com.products.backend.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.products.backend.domain.entities.Category;
import com.products.backend.domain.entities.Product;
import com.products.backend.domain.services.ProductService;
import com.products.backend.infra.util.json.GlobalJsonLocalDateTimeDeserializer;
import com.products.backend.infra.util.json.GlobalJsonLocalDateTimeUsingTimeZoneSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.spi.http.HttpHandler;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
@Api(tags = "Productos")
public class ProductRestContoller {

    private final ProductService productService;

    @ApiOperation(value = "Servicio que consume una Api de productos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Api de Productos consumida satisfactoriamente"),
            @ApiResponse(code = 500, message = "Error interno")})
    @GetMapping("/register")
    public ResponseEntity<?> getProducts() throws Exception {
        return new ResponseEntity<>(productService.getProductToRegister(UUID.randomUUID().toString()), HttpStatus.OK);
    }

    @ApiOperation(value = "Servicio que devuelve el listado de productos registrados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Productos obtenidos satisfactoriamente"),
            @ApiResponse(code = 500, message = "Error interno")})
    @GetMapping("/list")
    public ResponseEntity<?> getProductsList(@RequestParam(value = "id", required = false) Integer id,
                                             @RequestParam(value = "title", required = false) String title,
                                             @RequestParam(value = "creationAt", required = false) String creationAt,
                                             @RequestParam(value = "price", required = false) Double price,
                                             @RequestParam(value = "description", required = false) String description,
                                             @RequestParam(value = "categoryName", required = false) String categoryName) {
        List<Product> products =  productService.getProductList(id, title, creationAt, price, description, categoryName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> autenticarUsuario(String userName, String password){
        String pass = password;
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }
}
