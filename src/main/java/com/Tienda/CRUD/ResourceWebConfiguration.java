package com.Tienda.CRUD;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración para servir recursos estáticos como imágenes desde un directorio específico en el sistema de archivos.
 * Implementa la interfaz WebMvcConfigurer para personalizar el comportamiento del framework Spring MVC.
 */
@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer {
    /**
     * Configura el mapeo de recursos estáticos para que las solicitudes a "/images/**" se resuelvan desde el directorio físico "images/".
     *
     * @param registry El registro que gestiona los manejadores de recursos.
     */
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
	}
    
}
