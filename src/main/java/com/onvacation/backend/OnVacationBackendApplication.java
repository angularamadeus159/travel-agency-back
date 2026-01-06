package com.onvacation.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CLASE PRINCIPAL DE LA APLICACIÓN
 * 
 * Esta es la clase que inicia toda la aplicación Spring Boot.
 * 
 * @SpringBootApplication es una anotación que combina tres funciones:
 * 1. @Configuration: Indica que esta clase contiene configuración
 * 2. @EnableAutoConfiguration: Configura Spring Boot automáticamente
 * 3. @ComponentScan: Busca componentes (Controllers, Services, etc.) en este paquete
 * 
 * CONCEPTOS CLAVE:
 * - Spring Boot: Framework que simplifica la creación de aplicaciones Java
 * - Anotaciones: Metadatos que le dicen a Spring cómo comportarse
 * - IoC (Inversión de Control): Spring maneja la creación de objetos por ti
 */
@SpringBootApplication
public class OnVacationBackendApplication {

    /**
     * MÉTODO MAIN - PUNTO DE ENTRADA DE LA APLICACIÓN
     * 
     * Este método se ejecuta cuando inicias la aplicación.
     * SpringApplication.run() inicia el servidor web embebido (Tomcat)
     * y configura toda la aplicación.
     * 
     * @param args Argumentos de línea de comandos (opcional)
     */
    public static void main(String[] args) {
        // Configurar Java para usar IPv6 (necesario para Supabase)
        System.setProperty("java.net.preferIPv6Addresses", "true");
        System.setProperty("sun.net.inetaddr.ttl", "0");
        
        // Inicia la aplicación Spring Boot
        SpringApplication.run(OnVacationBackendApplication.class, args);
        
        // Mensaje en consola para confirmar que inició correctamente
        System.out.println("✅ Servidor iniciado en http://localhost:8080");
    }
}
