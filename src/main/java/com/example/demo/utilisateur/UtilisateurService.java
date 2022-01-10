/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.utilisateur;


import com.example.demo.categorie.Categorie;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author ratsi
 */
@Service
public class UtilisateurService {
    	@PersistenceContext
    private EntityManager entityManager;
    private  TransactionTemplate transactionTemplate;
    private TransactionStatus status;
    
    @Autowired
    private PlatformTransactionManager transactionManager;

         
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
    
    @Transactional
    void updateUtil(String idUtil, String email, String mdp) {
        Utilisateur u = uRepository.findById(idUtil)
                .orElseThrow(() -> new IllegalStateException(
                "utilisateur with id " + idUtil + " does not exists"));
        
        transactionTemplate = new TransactionTemplate(transactionManager);
        
        transactionTemplate.execute(status->{
        	 
        	String sql = "UPDATE Utilisateur SET ";
            Boolean misy = false;
            if(email!=null & email.length()>0){
                sql += "email = '"+email+"'";
                misy = true;
            }
            if(misy==true){ sql+=","; }
            if(mdp!=null & mdp.length()>0){
                sql += "mdp = HashBytes('SHA2_256', convert(varchar,'"+mdp+"'))";
               
            }
            sql+=" WHERE id_utilisateur = '"+idUtil+"'";
            
        	entityManager.createQuery(sql).executeUpdate();
        	status.flush();
        	return null;
        });
        
        
       
    }

   public List getUtilisateurs() {
        return uRepository.findAll();
   }
   public Utilisateur getUtilisateurByEmail(String email)
   {
      Optional<Utilisateur> uOptional = uRepository
                .findUtilByEmail(email);
      return uOptional.get();
      
   }

  

    void deleteUtil(String idUtil) {
        uRepository.deleteById(idUtil);
    }
    
	
}
