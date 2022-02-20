package com.example.demo.filter;

import com.example.demo.tokenFront.TokenFront;
import com.example.demo.tokenFront.TokenFrontService;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Component
@Order(2)
public class FilterFront implements Filter {

    @Autowired
    private final TokenFrontService tserv;

    public FilterFront(TokenFrontService tserv) {
        this.tserv = tserv;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type");

       
        String bearerToken = req.getHeader("Authorization");
        
        System.out.println("Authorization => "+bearerToken);
        System.out.println("Access-Control-Allow-Origin => "+req.getHeader("Access-Control-Allow-Origin"));
       
       if (bearerToken == null) {

            System.out.println("method:  " + req.getMethod() + " uri: " + req.getRequestURI());
            String baseURL = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
            System.out.println("CONTEXT PATH: " + baseURL);

            throw new IllegalStateException("Accès non autorisé, Token non Spécifié");
        } else {

            String[] list =  bearerToken.split("Bearer ");
    		String monTok =  list[1];
    		TokenFront t = new TokenFront();
            t.setIdToken(monTok);
            Optional<TokenFront> token = tserv.find(t);
            request.setAttribute("token", token);
            
            chain.doFilter(request, response);

        }
    }

    @Bean(name = "loggingFilter2")
    public FilterRegistrationBean<Filter> loggingFilter(final FilterFront filtre) {
        FilterRegistrationBean<Filter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(filtre);
        registrationBean.addUrlPatterns("/front/signalements/*");
        registrationBean.addUrlPatterns("/front/groupements/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }

}
