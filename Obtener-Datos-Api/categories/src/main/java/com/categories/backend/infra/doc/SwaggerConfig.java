package com.categories.backend.infra.doc;

import com.categories.backend.infra.http.SwaggerPage;
import com.categories.backend.infra.http.SwaggerPageable;
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
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String MSG_OK_200 = "Operación correcta.";
    public static final String MSG_ERROR_500 = "Error interno del servidor.";
    public static final String MSG_ERROR_404 = "Recurso no encontrado.";


    public static final Contact DEFAULT_CONTACT = new Contact(
            "Lorenzo Bárzaga Meriño", "https://github.com/lbarzagam/portafolio", "lbarzagam11@gmail.com");

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<>(Arrays.asList("application/json",
                    "application/xml"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT_API_INFO)
                .directModelSubstitute(Pageable.class, SwaggerPageable.class)
                .directModelSubstitute(Page.class, SwaggerPage.class)
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, getCustomizedResponseMessages())
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
            .title("API MICROSERVICE")
            .termsOfServiceUrl("urn:tos")
            .version("1.0.0")
            .description("Api del Microservicio")
            .contact(DEFAULT_CONTACT)
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
            .build();


    private List<Response> getCustomizedResponseMessages() {
        List<Response> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseBuilder().code("200").description(MSG_OK_200).build());
        responseMessages.add(new ResponseBuilder().code("404").description(MSG_ERROR_404).build());
        responseMessages.add(new ResponseBuilder().code("500").description(MSG_ERROR_500).build());
        return responseMessages;
    }
}
