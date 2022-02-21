package com.example.demo.filter;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.tokenFront.TokenFront;
import com.example.demo.tokenFront.TokenFrontService;

public class TokenFilter {

    @Autowired
    private TokenFrontService tserv;

    public TokenFilter() {
    }
    
	public TokenFront doFilter(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
        
        System.out.println("Authorization => "+bearerToken);
        TokenFront val = null;
       if (bearerToken == null) {
            throw new IllegalStateException("Accès non autorisé, Token non Spécifié");
        } else {

            String[] list =  bearerToken.split("Bearer ");
    		String monTok =  list[1];
    		TokenFront t = new TokenFront();
            t.setIdToken(monTok);
            Optional<TokenFront> token = tserv.find(t);
            val = token.get();
        }
       return val;
	}
}
