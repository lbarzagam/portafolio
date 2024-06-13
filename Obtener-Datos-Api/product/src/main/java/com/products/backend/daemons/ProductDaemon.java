package com.products.backend.daemons;


import com.products.backend.domain.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductDaemon {

    private static final String TIME_ZONE = "America/Havana";
    private final ProductService productService;

    /* Proceso de Liquidación Automática
    /* 0 15 10 ? * MON-FRI
    /* seg min horas dias mes año
    */
    @Transactional
    @Scheduled(cron = "${cron-act-productos}", zone = TIME_ZONE)
    public void productListDaemon() throws Exception {
        productService.getProductToRegister(UUID.randomUUID().toString());
    }
}
