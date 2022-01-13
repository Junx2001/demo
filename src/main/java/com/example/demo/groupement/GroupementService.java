package com.example.demo.groupement;

import java.time.LocalDate;
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
	    
}
