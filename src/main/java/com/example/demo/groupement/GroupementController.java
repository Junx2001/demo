package com.example.demo.groupement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/groupement")
public class GroupementController {
	
	@Autowired
	private  GroupementService service;
	public GroupementController(GroupementService service) {
	        this.service = service;
	    }
	
	
	 @PutMapping(path = "{idGroupement}")
     public void updateSignalement(
        @PathVariable("idGroupement") String idGroupement)
     {
         service.updateEtatGroupement(idGroupement);
     }
}
