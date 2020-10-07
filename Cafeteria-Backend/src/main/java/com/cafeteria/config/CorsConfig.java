package com.cafeteria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200", "http://localhost:3002", "http://localhost:19000", "http://localhost:19001", "http://localhost:19002", "http://localhost:19006", "http://localhost:8080", "*")
				.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH").allowedHeaders("*")
				.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Methods",
						"Access-Control-Allow-Headers", "Access-Control-Max-Age", "Access-Control-Request-Headers",
						"Access-Control-Request-Method")
				.allowCredentials(true);
	}

}