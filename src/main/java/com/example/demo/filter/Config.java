package com.example.demo.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Config  implements WebMvcConfigurer{
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**")
		.allowedOrigins("*")
		.allowedHeaders("*")
		.allowCredentials(true)
		.allowedMethods("OPTIONS","POST","GET","PUT","DELETE");
		
	}
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        if (!registry.hasMappingForPattern("/views/assets/**")) {
	            registry.addResourceHandler("/views/assets/**")
	                    .addResourceLocations("/views/assets/");
	        }
	    }
	
}
