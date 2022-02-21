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
	    String insertGroupement(String description,Double double1,Double double2,String nomImage,String region,String idSousCategorie) {
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
			      .setParameter(3, (double1))
			      .setParameter(4, (double2))
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

		public List<Groupement>  findGroupements(String region) {
			// TODO Auto-generated method stub
	        return repository.findGroupements(region);
		}
		
		public List<HashMap<String, Object>> hashMapGroupement(List<Object[]> liste) {
	        List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();

	        for (int i = 0; i < liste.size(); i++) {
	            HashMap<String, Object> hm = new HashMap<String, Object>();
	            Object[] s = (Object[]) liste.get(i);
	            hm.put("idGroupement", s[0]);
	            String str = "";
	            if (s[1]!=null) {
	            	str = new SimpleDateFormat("dd-MM-yyyy").format(s[1]);
	            }
	            hm.put("dateResolu", str);
	            hm.put("description", s[2]);
	            hm.put("latitude", s[3]);
	            hm.put("longitude", s[4]);
	            hm.put("nomImage", s[5]);
	            hm.put("nomRegion", s[6]);
	            hm.put("nomSousCat", s[7]);
	            hm.put("nomCat", s[8]);
	            hm.put("idRegion", s[9]);
	            hm.put("etat", s[10]);
	            
	            String nomCat = (String)s[8];
	            if (nomCat.compareTo("infrastructure")==0) {
	            	hm.put("couleur", "purple");
	            }else if (nomCat.compareTo("evenement")==0) {
	            	hm.put("couleur", "red");
	            }
	            listehm.add(hm);
	        }
	        return listehm;
	    }

		public List<HashMap<String, Object>> findDetailsGroupements(String region) {
			List<Object[]> liste =  repository.findDetailsGroupements(region);
			List<HashMap<String, Object>> listehm = this.hashMapGroupement(liste);
			// TODO Auto-generated method stub
			return null;
		}
		
		public HashMap<String, Object> getFicheGroupement(String idGroupement) {
			List<Object[]> liste = repository.getFicheGroupement(idGroupement);
	        List<HashMap<String, Object>> listehm = this.hashMapGroupement(liste);
	        return listehm.get(0);
		}
	    
	    
	    
}
