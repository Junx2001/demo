package com.example.demo.tokenFront;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class TokenFrontService {
	 @PersistenceContext
	 private EntityManager entityManager;
	 
	private final TokenFrontRepository repository;
	
	@Autowired
	public TokenFrontService(TokenFrontRepository repository) {
		this.repository = repository;
	}

	public Optional<TokenFront> find(TokenFront tok) {
		// TODO Auto-generated method stub
		Optional<TokenFront> u = repository.findByTokenId(tok.getIdToken());
		if (!u.isPresent()) {
			throw new IllegalStateException("Acces non Autorisé, Token Incorrect");
		}
        return u;
	}
        
        
        public void insertToken(String idUtil) {
		// TODO Auto-generated method stub
                TokenFront tok = new TokenFront();
                tok.setIdToken(idUtil+"MONTOK123"+LocalDateTime.now());
                tok.setIdUtilisateur(idUtil);
                tok.setDateExpiration(LocalDateTime.now());
                
		repository.save(tok);
	}

	

}