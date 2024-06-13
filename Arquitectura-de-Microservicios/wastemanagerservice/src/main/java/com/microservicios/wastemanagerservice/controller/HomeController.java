package com.microservicios.wastemanagerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public ResponseEntity toHomePage() {
        System.out.println("To Home");
        return new ResponseEntity<>("Bienvenido a WasteManagerService", HttpStatus.OK);
    }
}
