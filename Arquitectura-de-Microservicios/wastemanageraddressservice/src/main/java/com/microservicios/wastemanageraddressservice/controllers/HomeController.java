package com.microservicios.wastemanageraddressservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public ResponseEntity toHomePage(){
        return new ResponseEntity<>("Bienvenido a WasteManagerAddressService", HttpStatus.OK);
    }
}
