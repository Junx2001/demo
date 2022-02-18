package com.example.demo.groupement;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.signalement.SignalementService;
import com.example.demo.tokenFront.TokenFront;
import com.example.demo.utilisateur.Utilisateur;
import com.example.demo.utilisateur.UtilisateurService;


@RestController
@RequestMapping(path = "/front/groupements")
public class GroupementControllerFront {
	
	@Autowired
	private  GroupementService service;
	
	 @Autowired
	private  UtilisateurService uService;
	 
	public GroupementControllerFront(GroupementService service) {
	        this.service = service;
	    }
	
	@GetMapping
	public List<Groupement> getAllGroupement( HttpServletRequest request)
	{
		Optional<TokenFront> otok = (Optional<TokenFront>)request.getAttribute("token");
        TokenFront tok = otok.get();
        
        Utilisateur u = uService.getUtilisateurById(tok.getIdUtilisateur());
        List<Groupement> val =  service.findGroupements(u.getRegion());
        return  val;
        
	}
	 @PutMapping(path = "/{idGroupement}")
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
