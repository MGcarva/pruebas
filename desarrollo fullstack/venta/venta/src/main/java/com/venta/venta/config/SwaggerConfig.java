package com.venta.venta.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ventaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 2026 - Gestión de Ventas")
                        .version("1.0")
                        .description("Documentación de la API para el microservicio de ventas")
                        .contact(new Contact()
                                .name("Equipo de Ventas")
                                .email("ventas@market.cl")
                                .url("https://market.cl")
                        )
                );
    }
}
