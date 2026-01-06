package com.onvacation.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CONFIGURACIÓN DE CORS (Cross-Origin Resource Sharing)
 * 
 * ¿QUÉ ES CORS?
 * Es un mecanismo de seguridad del navegador que impide que un sitio web
 * haga peticiones a otro dominio diferente.
 * 
 * EJEMPLO:
 * - Frontend: http://localhost:4200 (Angular)
 * - Backend: http://localhost:8080 (Spring Boot)
 * 
 * Sin CORS, el navegador bloquea las peticiones del frontend al backend
 * porque están en puertos diferentes.
 * 
 * ESTA CONFIGURACIÓN:
 * Permite que Angular (localhost:4200) pueda hacer peticiones al backend.
 */
@Configuration
public class CorsConfig {

    /**
     * Configuración de CORS
     * 
     * @Bean: Le dice a Spring que este método crea un objeto (bean)
     * que debe ser gestionado por el contenedor de Spring
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Aplica CORS a todos los endpoints que empiecen con /api
                        .allowedOrigins("http://localhost:4200") // Permite peticiones desde Angular
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                        .allowedHeaders("*") // Permite todos los headers
                        .allowCredentials(true) // Permite cookies y autenticación
                        .maxAge(3600); // Cachea la configuración CORS por 1 hora
            }
        };
    }
}
