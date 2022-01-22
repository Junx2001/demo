package com.example.demo.userFinal;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserFinalService {
	 @PersistenceContext
	 private EntityManager entityManager;
	 
	private final UserFinalRepository repository;
	
	@Autowired
	public UserFinalService(UserFinalRepository repository) {
		this.repository = repository;
	}

	public Optional<UserFinal> find(UserFinal util) {
		// TODO Auto-generated method stub
		Optional<UserFinal> u = repository.findUserFinalByEmailAndMdp(util.getEmail(),util.getMdp());
		if (!u.isPresent()) {
			throw new IllegalStateException("Veuillez verifier votre mot de passe et votre email");
		}
        return u;
	}

	@Transactional
	public void insertWithQuery(UserFinal u) {
		  	Optional<UserFinal> uOptional = repository.findUserFinalByEmail(u.getEmail());
	        if (uOptional.isPresent()) {
	            throw new IllegalStateException("L'email "+ u.getEmail()+" est déja associé à un compte, veuillez vous connecter");
	        }
	        entityManager.createNativeQuery("INSERT INTO user_final"
	        		+ " VALUES (CONCAT('U',NEXT VALUE FOR seq_user_final),?,HashBytes('SHA2_256', convert(varchar,?)))")
	        .setParameter(1, u.getEmail())
                        
	        .setParameter(2, u.getMdp())
	        .executeUpdate();
		
	}

}