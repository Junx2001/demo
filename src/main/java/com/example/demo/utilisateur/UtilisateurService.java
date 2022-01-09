/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.utilisateur;


import com.example.demo.categorie.Categorie;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ratsi
 */
@Service
public class UtilisateurService {
    	@PersistenceContext
    private EntityManager entityManager;
	
   private final UtilisateurRepository uRepository;

   
    @Autowired
    public UtilisateurService(UtilisateurRepository uRepository) {
        this.uRepository = uRepository;
    }

    @Transactional
    public void insertWithQuery(Utilisateur u) {
          Optional<Utilisateur> uOptional = uRepository
                .findUtilByEmail(u.getEmail());
        if (uOptional.isPresent()) {
            throw new IllegalStateException("L'email "+ u.getEmail()+" est déja associé à un compte, veuillez vous connecter");
        }
        entityManager.createNativeQuery("INSERT INTO utilisateur VALUES (CONCAT('U',NEXT VALUE FOR seq_utilisateur),?,HashBytes('SHA2_256', convert(varchar,?)))")
        .setParameter(1, u.getEmail())
        .setParameter(2, u.getMdp())
        .executeUpdate();
    }
    
	
}
