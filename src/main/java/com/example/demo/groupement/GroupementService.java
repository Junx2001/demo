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
	    void insertGroupement(String description,String latitude,String longitude,String nomImage,String region,String idSousCategorie) {
	    	String liste=repository.getNextSequence();
	    	
		      //.executeUpdate();
	    	Groupement g = new Groupement();
	    	g.setIdGroupement(liste);
	    	g.setDescription(description);
	    	g.setLatitude(Double.parseDouble(latitude));
	    	g.setLongitude(Double.parseDouble(longitude));
	    	g.setRegion(region);
	    	g.setIdSousCategorie(idSousCategorie);
	    	System.out.println(longitude);
	    	//repository.save(g);
	    	/*entityManager.createNativeQuery("insert into groupement (idGroupement,description,latitude,longitude,nomImage,region,idSousCategorie) values (?,?,?,?,?,?,?)")
		      .setParameter(1, g.getIdGroupement())
		      .setParameter(2, g.getDescription())
		      .setParameter(3, g.getLatitude())
		      .setParameter(4, g.getLongitude())
		      .setParameter(5, g.getNomImage())
		      .setParameter(6, g.getRegion())
		      .setParameter(7, g.getIdSousCategorie())
		      .executeUpdate();*/
	    	
	    	transactionTemplate = new TransactionTemplate(transactionManager);
	        
	        transactionTemplate.execute(status->{
	        	entityManager.createQuery("insert into groupement (idGroupement,description,latitude,longitude,nomImage,region,idSousCategorie) " + 
	        			"values ('17','faty olona',15.265,18.66,'blabla','4','SC1')")
			      /*.setParameter(1, g.getIdGroupement())
			      .setParameter(2, g.getDescription())
			      .setParameter(3, g.getLatitude())
			      .setParameter(4, g.getLongitude())
			      .setParameter(5, g.getRegion())
			      .setParameter(6, g.getIdSousCategorie())*/
			      .executeUpdate();
	        	
	        	status.flush();
	        	return null;
	        });

	    }
	    
	    
}
