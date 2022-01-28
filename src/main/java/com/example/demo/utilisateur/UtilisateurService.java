/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.utilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.demo.fonction.Fonction;



/**
 *
 * @author ratsi
 */
@Service
public class UtilisateurService {
    @PersistenceContext
    private EntityManager entityManager;
    private  TransactionTemplate transactionTemplate;
    
    @Autowired
    private PlatformTransactionManager transactionManager;

         
    private final UtilisateurRepository uRepository;

   
    @Autowired
    public UtilisateurService(UtilisateurRepository uRepository) {
        this.uRepository = uRepository;
    }

    @Transactional
    public void insertWithQuery(Utilisateur u) {
    	if (!Fonction.verifEmail(u.getEmail())) {
    		throw new IllegalStateException("La syntaxe de l'email doit être de la forme xxx@xxx.xx");
		}
		if (!Fonction.verifMdp(u.getMdp())) {
    		throw new IllegalStateException("Le mot de passe inséré doit contenir au moins 8 caratères et aucun accent");
		}
    	
          Optional<Utilisateur> uOptional = uRepository
                .findUtilByEmail(u.getEmail());
          Optional<Utilisateur> uOptionalRegion = uRepository
                  .findUtilByRegion(u.getRegion());
        if (uOptional.isPresent()) {
            throw new IllegalStateException("L'email "+ u.getEmail()+" est déja associé à un compte, veuillez vous connecter");
        }
        if (uOptionalRegion.isPresent()) {
            throw new IllegalStateException("La region numéro "+ u.getRegion()+" est déja associée à un autre utilisateur");
        }
        entityManager.createNativeQuery("INSERT INTO utilisateur"
        		+ " VALUES (CONCAT('U',NEXT VALUE FOR seq_utilisateur),?,HashBytes('SHA2_256', convert(varchar,?)),?)")
        .setParameter(1, u.getEmail())
        .setParameter(2, u.getMdp())
        .setParameter(3, u.getRegion())
        .executeUpdate();
    }
    
    @Transactional
    public void updateUtil(String idUtil, String email, String mdp, String idRegion) {
    	uRepository.findById(idUtil)
                .orElseThrow(() -> new IllegalStateException(
                "utilisateur with id " + idUtil + " does not exists"));
        
        transactionTemplate = new TransactionTemplate(transactionManager);
        
        transactionTemplate.execute(status->{
        	 
        	String sql = "UPDATE Utilisateur SET ";
            Boolean misy = false;
            if(email!=null ){
            	if (!Fonction.verifEmail(email)) {
            		throw new IllegalStateException("La syntaxe de l'email doit être de la forme xxx@xxx.xx");
        		}
            	
                sql += "email = '"+email+"'";
                misy = true;
            }
            if(misy==true && mdp!=null){ sql+=","; }
            if(mdp!=null){
            	if (!Fonction.verifMdp(mdp)) {
            		throw new IllegalStateException("Le mot de passe inséré doit au moins contenir 8 caratères et aucun accent");
        		}
            	
                sql += "mdp = HashBytes('SHA2_256', convert(varchar,'"+mdp+"'))";
                misy = true;
               
            }
            if(misy==true && idRegion!=null){ sql+=","; }
            if(idRegion!=null){
                sql += "region = '"+idRegion+"'";
                misy = true;
            }
            sql+=" WHERE id_utilisateur = '"+idUtil+"'";
            System.out.println(sql);
            
        	entityManager.createQuery(sql).executeUpdate();
        	status.flush();
        	return null;
        });
    }

   public List<HashMap<String, Object>> getUtilisateurs() {
	  List<Object[]> liste = uRepository.getViewUtilisateur();
  	 List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
       for (int i = 0; i < liste.size(); i++) {
           HashMap<String, Object> hm = new HashMap<String, Object>();
           Object[] s = (Object[]) liste.get(i);
           hm.put("idUtilisateur", s[0]);
           hm.put("email", s[1]);
           hm.put("mdp", s[2]);
           hm.put("idRegion", s[3]);
           hm.put("nomRegion", s[4]);
           listehm.add(hm);
       }
       return listehm;
   }
   public Utilisateur getUtilisateurByEmail(String email)
   {
      Optional<Utilisateur> uOptional = uRepository
                .findUtilByEmail(email);
      return uOptional.get();
      
   }
   
   public Utilisateur getUtilisateurById(String idUtil)
   {
      Optional<Utilisateur> uOptional = uRepository
                .findById(idUtil);
      return uOptional.get();
      
   }

  

    void deleteUtil(String idUtil) {
        uRepository.deleteById(idUtil);
    }

	public Optional<Utilisateur> find(Utilisateur util) {
		
		if (!Fonction.verifEmail(util.getEmail())) {
    		throw new IllegalStateException("La syntaxe de votre email est incorrect");
		}
		if (!Fonction.verifMdp(util.getMdp())) {
    		throw new IllegalStateException("Votre mot de passe doit contenir 8 caratères et aucun accent");
		}
		
		
		Optional<Utilisateur> u = uRepository.findUtilisateurByEmailAndMdp(util.getEmail(),util.getMdp());
		
		if (!u.isPresent()) {
			throw new IllegalStateException("Veuillez verifier votre mot de passe et votre email");
		}
        return u;
	}
    
	
}
