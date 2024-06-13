package com.microservicios.namingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NamingService {
    public static void main(String[] args) {
        try {
            SpringApplication.run(NamingService.class, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
