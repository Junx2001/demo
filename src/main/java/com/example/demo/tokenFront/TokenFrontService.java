package com.example.demo.tokenFront;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.codec.digest.DigestUtils;

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
        
        
        public String insertToken(String idUtil) {
		// TODO Auto-generated method stub
                TokenFront tok = new TokenFront();
                String myTok = DigestUtils.sha256Hex(idUtil+"MONTOK123"+LocalDateTime.now());
                tok.setIdToken(myTok);
                tok.setIdUtilisateur(idUtil);
                tok.setDateExpiration(LocalDateTime.now().plusDays(14));
                
		repository.save(tok);
                return myTok;
	}

	

}