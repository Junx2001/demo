package com.example.demo.utilisateur;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/front/utilisateur")
public class UtilisateurControllerFront {
	 @Autowired
	 private UtilisateurService uService;

	    public UtilisateurControllerFront(UtilisateurService uService) {
	        this.uService = uService;
	    }
	    
	    @PostMapping("/login")
	    public @ResponseBody Optional<Utilisateur> login(Utilisateur util)
	    {
	    	return  uService.find(util);
	    }
}
