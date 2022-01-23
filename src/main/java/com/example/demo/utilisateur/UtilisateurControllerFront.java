package com.example.demo.utilisateur;

import com.example.demo.signalement.SignalementService;
import com.example.demo.tokenFront.TokenFrontService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/front")
public class UtilisateurControllerFront {
	 @Autowired
	 private UtilisateurService uService;
         
	 
         @Autowired
	 private TokenFrontService tokService;

	    public UtilisateurControllerFront(UtilisateurService uService) {
	        this.uService = uService;
	    }
	    
	    @PostMapping("/utilisateur/login")
	    public @ResponseBody String login(Utilisateur util)
	    {     
	    	Optional<Utilisateur> u = uService.find(util);
                String t = tokService.insertToken(u.get().getIdUtilisateur());
                return "L'utilisateur Front Office avec l'email "+u.get().getEmail()+" s'est connecté\n Il obtient un token d'authentification : "+t;
	    }
	    
	    
}
