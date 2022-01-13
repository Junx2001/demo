package com.example.demo.groupement;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class GroupementService {
	 private final GroupementRepository repository;

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
	    void insertGroupement(String idGroup) {
	    	Groupement g = repository.findById(idGroup)
	    			.orElseThrow(() -> new IllegalStateException(
	    	                "le groupement avec l'id  " + idGroup + " n'existe pas"));
	    	
	    	g.setEtat("1");
	    	LocalDate lt = LocalDate.now();
	    	g.setDateResolu(lt);
	    	repository.save(g);

	    }
	    
	    public List<HashMap<String, Object>> getSignalements() {
	    	String liste=repository.getNextSequence();
	    	 /*List<Object[]> liste = signRepository.getDetailsSignalements();
	    	 List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
	         for (int i = 0; i < liste.size(); i++) {
	             HashMap<String, Object> hm = new HashMap<String, Object>();
	             Object[] s = (Object[]) liste.get(i);
	             hm.put("idSignalement", s[0]);
	             String str = new SimpleDateFormat("dd-MM-yyyy").format(s[1]);
	             hm.put("dateSignalement", str);
	             hm.put("description", s[2]);
	             hm.put("latitude", s[3]);
	             hm.put("longitude", s[4]);
	             hm.put("nomImage", s[5]);
	             hm.put("region", s[6]);
	             hm.put("nomSousCat", s[7]);
	             hm.put("nomCat", s[8]);
	             hm.put("email", s[9]);
	             listehm.add(hm);
	         }*/
	         return listehm;
	    }
	    
	    
}
