package com.inventario.inventario.config;

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
                        .title("API 2026 Gestión de Inventario")
                        .version("1.0")
                        .description("Documentación de la API para el sistema de gestión de inventario de productos")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("soporte@duoc.cl")
                                .url("https://duoc.cl")
                        )
                        
                );
    }
}
