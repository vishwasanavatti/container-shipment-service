package com.kn.containershipment.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer

import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class ApiConfig {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            /** @descriptions This method configures cross origin requests
             */
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .maxAge(3600)
            }

            /** @descriptions This method restricts content type to application/json
             */
            override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
                configurer.defaultContentType(MediaType.APPLICATION_JSON)
            }
        }
    }
}