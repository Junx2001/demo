package com.example.demo.groupement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.signalement.SignalementService;


@RestController
@RequestMapping(path = "/front/groupement")
public class GroupementControllerFront {
	
	@Autowired
	private  GroupementService service;
	public GroupementControllerFront(GroupementService service) {
	        this.service = service;
	    }
	
	 @PutMapping(path = "{idGroupement}")
     public void updateEtatSignalement(
        @PathVariable("idGroupement") String idGroupement)
     {
         service.updateEtatGroupement(idGroupement);
     }
	 
	 @PostMapping
     public String insertNewGroupement(
    		@PathVariable("description")
			@RequestParam(required = false)
    		 String description,
    		@PathVariable("latitude")
			@RequestParam(required = false)
    		 String latitude,
    		@PathVariable("longitude")
			@RequestParam(required = false)
    		 String longitude,
    		@PathVariable("region")
			@RequestParam(required = false)
    		 String region,
    		@PathVariable("idSousCategorie")
			@RequestParam(required = false)
    		 String idSousCategorie)
     {
         return service.insertGroupement(description, latitude, longitude,null, region, idSousCategorie);
     }
	 
	
}
