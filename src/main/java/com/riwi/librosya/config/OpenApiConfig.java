package com.riwi.librosya.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API para gestionar bibioteca virtual LibrosYa ", version = "1.0", description = "Documentacion API de adminitracion de entidades del sistema de gestión de biblioteca"))
public class OpenApiConfig {

}