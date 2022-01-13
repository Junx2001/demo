package com.example.demo.groupement;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@Autowired
	private  SignalementService signService;
	
	 @PutMapping(path = "{idGroupement}")
     public void updateEtatSignalement(
        @PathVariable("idGroupement") String idGroupement)
     {
         service.updateEtatGroupement(idGroupement);
     }
	 
	
}
