package com.example.Marcket.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

@Bean
public OpenAPI customOpenAPI() {
return new OpenAPI()
        .info(new Info()
                .title("API 2026 Gestión de Administradores")
                .version("1.0")
                .description("Documentación de la API para la gestión de usuarios administradores del sistema Market")
                .contact(new Contact()
                        .name("Soporte Técnico")
                        .email("soporte@market.cl")
                        .url("https://market.cl")
                )
        );
}
}
