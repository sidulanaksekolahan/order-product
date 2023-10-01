package com.example.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    // Open http://localhost:8080/swagger-ui/index.html

    @Bean
    public OpenAPI basiliskOpenAPI(){

        Info info = new Info().title("Product Order")
                .description("Demo REST API untuk test interview")
                .version("v 1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));


        OpenAPI openAPI = new OpenAPI()
                .info(info);

        return openAPI;
    }

}
