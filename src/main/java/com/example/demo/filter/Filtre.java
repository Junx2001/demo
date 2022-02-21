package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Filtre implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession();
        if (session.getAttribute("administrateur")==null) {
        	System.out.println("method:  "+req.getMethod()+" uri: "+ req.getRequestURI());
        	String baseURL = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
        	System.out.println("CONTEXT PATH: "+baseURL);
        	
        	res.sendRedirect(baseURL+"/back/administrateurs/login");
        }
        chain.doFilter(request, response);
       
	}
	
	@Bean(name="loggingFilter1")
	public FilterRegistrationBean<Filter> loggingFilter(){
	    FilterRegistrationBean<Filter> registrationBean 
	      = new FilterRegistrationBean<>();
	        
	    registrationBean.setFilter(new Filtre());
	    registrationBean.addUrlPatterns("/back/signalements/*");
	    registrationBean.addUrlPatterns("/back/categories/*");
	    registrationBean.addUrlPatterns("/back/regions/*");
	    registrationBean.addUrlPatterns("/back/sousCategories/*");
	    registrationBean.addUrlPatterns("/back/utilisateurs/*");
	    registrationBean.setOrder(2);
	        
	    return registrationBean;    
	}
	
}