package com.example.demo.filter;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.tokenMobile.TokenMobile;
import com.example.demo.tokenMobile.TokenMobileService;

public class TokenMobileFilter {
	 @Autowired
	    private final TokenMobileService tserv;

	    public TokenMobileFilter(TokenMobileService tserv) {
	    	this.tserv = tserv;
	    }
	    
		public TokenMobile doFilter(HttpServletRequest request) {
			String bearerToken = request.getHeader("Authorization");
	        
			TokenMobile val = null;
	       if (bearerToken == null) {
	            throw new IllegalStateException("Accès non autorisé, Token non Spécifié");
	        } else {

	            String[] list =  bearerToken.split("Bearer ");
	    		String monTok =  list[1];
	    		TokenMobile t = new TokenMobile();
	            t.setIdToken(monTok);
	            System.out.println("Authorization => "+monTok);
	            Optional<TokenMobile> token = tserv.find(t);
	            System.out.println("Authorization => "+token);
	            val = token.get();
	        }
	       return val;
		}
}
