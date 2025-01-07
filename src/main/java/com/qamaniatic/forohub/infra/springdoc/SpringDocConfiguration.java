package com.qamaniatic.forohub.infra.springdoc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("ForoHub API")
                        .description("Documentación del API Rest de ForoHub. Esta API proporciona funcionalidades CRUD para la gestión de tópicos de discusión, así como un endpoint de usuarios para autenticación y generación de tokens JWT necesarios para acceder a los otros endpoints.")
                        .contact(new Contact()
                                .name("Angelo Huaraca")
                                .email("ahuaracab@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://forohub.com/api/licencia")));
    }
}