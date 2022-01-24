package com.example.demo.userFinal;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.fonction.Fonction;




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
		if (!Fonction.verifEmail(util.getEmail())) {
    		throw new IllegalStateException("La syntaxe de votre email est incorrect");
		}
		if (!Fonction.verifMdp(util.getMdp())) {
    		throw new IllegalStateException("Le mot de passe inséré doit contenir 8 caratères et aucun accent");
		}
		
		Optional<UserFinal> u = repository.findUserFinalByEmailAndMdp(util.getEmail(),util.getMdp());
		if (!u.isPresent()) {
			throw new IllegalStateException("Veuillez verifier votre mot de passe et votre email");
		}
        return u;
	}

	@Transactional
	public void insertWithQuery(UserFinal u) {
		if (!Fonction.verifEmail(u.getEmail())) {
    		throw new IllegalStateException("La syntaxe de l'email doit être de la forme xxx@xxx.xx");
		}
		if (!Fonction.verifMdp(u.getMdp())) {
    		throw new IllegalStateException("Le mot de passe inséré doit contenir 8 caratères et aucun accent");
		}
		
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