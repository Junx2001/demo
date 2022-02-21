package com.example.demo.groupement;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.filter.TokenFilter;
import com.example.demo.signalement.SignalementService;
import com.example.demo.tokenFront.TokenFront;
import com.example.demo.tokenFront.TokenFrontService;
import com.example.demo.utilisateur.Utilisateur;
import com.example.demo.utilisateur.UtilisateurService;

@CrossOrigin
@RestController
@RequestMapping(path = "/front/groupements")
public class GroupementControllerFront {
	@Autowired
	private  TokenFrontService tserv;
	
	@Autowired
	private  GroupementService service;
	
	 @Autowired
	private  UtilisateurService uService;
	 
	public GroupementControllerFront(GroupementService service) {
	        this.service = service;
	    }
	
	@GetMapping
	public  List<Groupement> getAllGroupement( HttpServletRequest request)
	{
		TokenFilter filtre = new TokenFilter(tserv);
        TokenFront tok = filtre.doFilter(request);
        
        Utilisateur u = uService.getUtilisateurById(tok.getIdUtilisateur());
        List<Groupement> val =  service.findGroupements(u.getRegion());
        return  val;
        
	}
	
	@GetMapping("/details")
	public  List<HashMap<String, Object>> getDetailsGroupement( HttpServletRequest request)
	{
		TokenFilter filtre = new TokenFilter(tserv);
        TokenFront tok = filtre.doFilter(request);
        
        Utilisateur u = uService.getUtilisateurById(tok.getIdUtilisateur());
        List<HashMap<String, Object>> val =  service.findDetailsGroupements(u.getRegion());
        return  val;
        
	}
	 @GetMapping("/{idGroupement}")
	    public HashMap<String,Object> ficheGroupement(
	    		@PathVariable("idGroupement") String idGroupement,HttpServletRequest request)
	    {
		 	TokenFilter filtre = new TokenFilter(tserv);
	        filtre.doFilter(request);
	    	return service.getFicheGroupement(idGroupement);
	    }
	
	 @PutMapping("/{idGroupement}")
     public @ResponseBody void updateEtatSignalement(
    		 @PathVariable("idGroupement") String idGroupement,HttpServletRequest request)
     {
		 TokenFilter filtre = new TokenFilter(tserv);
	        filtre.doFilter(request);
	        System.out.println("222222222222: "+idGroupement);
         service.updateEtatGroupement(idGroupement);
     }
	 
	 @PostMapping
     public @ResponseBody String insertNewGroupement(
    		 Groupement gr,
    		 HttpServletRequest request)
     {
		 TokenFilter filtre = new TokenFilter(tserv);
		 TokenFront tok =  filtre.doFilter(request);
	     Utilisateur u = uService.getUtilisateurById(tok.getIdUtilisateur());
         return service.insertGroupement(gr.getDescription(), gr.getLatitude(), gr.getLongitude(),gr.getNomImage(), u.getRegion(), gr.getIdSousCategorie());
     }
	 
	
}
