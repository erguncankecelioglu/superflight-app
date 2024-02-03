package com.superflight1.util.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Super Flight Rest API",
                version = "1.0.0",
                description = "WELCOME TO SUPER FLIGHT - This is a REST API for Super Flight.",
                license = @License(name = "Apache License Version 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
        )
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("superflight")
                .pathsToMatch("/**")
                .build();
    }
}
