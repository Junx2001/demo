package com.example.demo.groupement;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;



@Service
public class GroupementService {
	 private final GroupementRepository repository;

	 	@PersistenceContext 
		private EntityManager entityManager;
	 	private  TransactionTemplate transactionTemplate;
	 	
	 	@Autowired
	    private PlatformTransactionManager transactionManager;
	 	
	    @Autowired
	    public GroupementService(GroupementRepository repository) {
	        this.repository = repository;
	    }
	    
	    public List<Groupement> getGroupements(){
	    	return repository.findAll();
	    }
	    
	    @Transactional
	    void updateEtatGroupement(String idGroup) {
	    	Groupement g = repository.findById(idGroup)
	    			.orElseThrow(() -> new IllegalStateException(
	    	                "le groupement avec l'id  " + idGroup + " n'existe pas"));
	    	
	    	g.setEtat("1");
	    	LocalDate lt = LocalDate.now();
	    	g.setDateResolu(lt);
	    	repository.save(g);

	    }
	    
	    @Transactional
	    String insertGroupement(String description,String latitude,String longitude,String nomImage,String region,String idSousCategorie) {
	    	String liste=repository.getNextSequence();
	    	System.out.println(region);
	    	System.out.println(description);
	    	System.out.println(nomImage);
	    	System.out.println(idSousCategorie);
	    	transactionTemplate = new TransactionTemplate(transactionManager);
	        
	        return transactionTemplate.execute(status->{
	        	
		    	entityManager.createNativeQuery("insert into groupement (id_groupement,description,latitude,longitude,nom_image,region,id_sous_categorie) values (?,?,?,?,?,?,?)")
			      .setParameter(1, liste)
			      .setParameter(2, description)
			      .setParameter(3, Double.parseDouble(latitude))
			      .setParameter(4, Double.parseDouble(longitude))
			      .setParameter(5, nomImage)
			      .setParameter(6, region)
			      .setParameter(7, idSousCategorie)
			      .executeUpdate();
			      
	        	
	        	status.flush();
	        	return liste;
	        });

	    }

		public List<Groupement> findAll() {
			// TODO Auto-generated method stub
			return repository.findAll();
		}

		public List<Groupement> findGroupements(String region) {
			// TODO Auto-generated method stub
			
			return repository.findGroupements(region);
		}
	    
	    
	    
}
