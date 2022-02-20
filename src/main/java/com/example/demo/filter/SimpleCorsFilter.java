package com.example.demo.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Component
public class SimpleCorsFilter {

	    @Bean
	    public FilterRegistrationBean<CorsFilter> initCorsFilter() {
	    	List<String> origins = new ArrayList<String>();
	    	origins.add("http://localhost:4200/**");
	    	origins.add("https://spring-joharisoa.herokuapp.com/**");
	    	origins.add("https://signgovfo.herokuapp.com/**");
	    	origins.add("http://spring-joharisoa.herokuapp.com/**");
	    	origins.add("http://signgovfo.herokuapp.com/**");
	        // @formatter:off
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
	        config.addAllowedMethod("*");
	        config.setAllowedOrigins(origins);
	        source.registerCorsConfiguration("/**", config);
	        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
	        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	        System.out.println("TTTTTTTTTTSSSSSSSS");
	        return bean;
	        // @formatter:on
	    }

}