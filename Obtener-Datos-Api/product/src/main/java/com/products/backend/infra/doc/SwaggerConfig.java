package com.products.backend.infra.doc;

import com.products.backend.infra.http.SwaggerPage;
import com.products.backend.infra.http.SwaggerPageable;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import io.swagger.v3.oas.models.info.Contact;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API MICROSERVICE")
                        .description("Sample app Spring Boot with Swagger")
                        .title("Api del Microservicio")
                        .version("1.0")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(DEFAULT_CONTACT)
                );

    }

    public static final Contact DEFAULT_CONTACT = new  Contact()
            .name("Lorenzo Bárzaga Meriño")
            .email("lbarzagam11@gmail.com")
            .url("https://github.com/lbarzagam/portafolio");
}
