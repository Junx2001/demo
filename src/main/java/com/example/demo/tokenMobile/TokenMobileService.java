package com.example.demo.tokenMobile;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class TokenMobileService {
	 @PersistenceContext
	 private EntityManager entityManager;
	 
	private final TokenMobileRepository repository;
	
	@Autowired
	public TokenMobileService(TokenMobileRepository repository) {
		this.repository = repository;
	}

	public Optional<TokenMobile> find(TokenMobile tok) {
		// TODO Auto-generated method stub
		Optional<TokenMobile> u = repository.findByTokenId(tok.getIdToken());
		if (!u.isPresent()) {
			throw new IllegalStateException("Acces non Autorisé, Token Incorrect");
		}
        return u;
	}
        
        
        public String insertToken(String idUser) {
		// TODO Auto-generated method stub
                TokenMobile tok = new TokenMobile();
                String valueTok = DigestUtils.sha256Hex(idUser+"THETOK123"+LocalDateTime.now());
                tok.setIdToken(valueTok);
                tok.setIdUserFinal(idUser);
                tok.setDateExpiration(LocalDateTime.now());
                
		repository.save(tok);
                return valueTok;
	}

	

}