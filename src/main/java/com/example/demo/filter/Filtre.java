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
@Order(1)
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
        	
        	res.sendRedirect(baseURL+"/administrateur/login");
        }
        chain.doFilter(request, response);
       
	}
	
	@Bean
	public FilterRegistrationBean<Filter> loggingFilter(){
	    FilterRegistrationBean<Filter> registrationBean 
	      = new FilterRegistrationBean<>();
	        
	    registrationBean.setFilter(new Filtre());
	    registrationBean.addUrlPatterns("/back/signalement/*");
	    registrationBean.addUrlPatterns("/back/categorie/*");
	    registrationBean.addUrlPatterns("/back/regions/*");
	    registrationBean.addUrlPatterns("/back/sousCategorie/*");
	    registrationBean.addUrlPatterns("/back/utilisateur/*");
	    registrationBean.setOrder(1);
	        
	    return registrationBean;    
	}
	
}