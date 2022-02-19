package com.example.demo.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class EnableCors {
	@Bean
    public WebMvcConfigurer configurer(){
      return new WebMvcConfigurer(){
        @Override
        public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/*")
              .allowedOrigins("*");
        }
      };
	}
}
