package com.example.demo.utilisateur;

import com.example.demo.tokenFront.TokenFrontService;
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
         
         @Autowired
	 private TokenFrontService tokService;

	    public UtilisateurControllerFront(UtilisateurService uService) {
	        this.uService = uService;
	    }
	    
	    @PostMapping("/login")
	    public @ResponseBody Optional<Utilisateur> login(Utilisateur util)
	    {     
	    	Optional<Utilisateur> u = uService.find(util);
                tokService.insertToken(util.getIdUtilisateur());
                return u;
	    }
}
