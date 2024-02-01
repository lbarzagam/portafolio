package com.products.backend.daemons;


import com.products.backend.domain.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class ProductDaemon {

    private static final String TIME_ZONE = "America/Havana";
    private final ProductService productService;

    @Transactional
    @Scheduled(cron = "${cron-act-productos}", zone = TIME_ZONE)
    public void productListDaemon(){

    }
}
