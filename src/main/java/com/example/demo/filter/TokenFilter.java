package com.example.demo.filter;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.tokenFront.TokenFront;
import com.example.demo.tokenFront.TokenFrontService;

public class TokenFilter {

    @Autowired
    private final TokenFrontService tserv;

    public TokenFilter(TokenFrontService tserv) {
    	this.tserv = tserv;
    }
    
	public TokenFront doFilter(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
        
        TokenFront val = null;
       if (bearerToken == null) {
            throw new IllegalStateException("Accès non autorisé, Token non Spécifié");
        } else {

            String[] list =  bearerToken.split("Bearer ");
    		String monTok =  list[1];
    		TokenFront t = new TokenFront();
            t.setIdToken(monTok);
            System.out.println("Authorization => "+monTok);
            Optional<TokenFront> token = tserv.find(t);
            System.out.println("Authorization => "+token);
            val = token.get();
        }
       return val;
	}
}
