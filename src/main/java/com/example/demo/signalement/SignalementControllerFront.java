package com.example.demo.signalement;

import com.example.demo.filter.TokenFilter;
import com.example.demo.tokenFront.TokenFront;
import com.example.demo.tokenFront.TokenFrontService;
import com.example.demo.utilisateur.Utilisateur;
import com.example.demo.utilisateur.UtilisateurService;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "https://signgovfo.herokuapp.com/signalement", allowedHeaders="*")@RestController
@RequestMapping(path = "/front/signalements")
public class SignalementControllerFront {
	
	
	@Autowired
	private  SignalementService signService;
        
    @Autowired
	private  UtilisateurService uService;
	
	public SignalementControllerFront(SignalementService signService) {
	    this.signService = signService;
	}
	
    @GetMapping
    public List rechercheSignalementFront(
       @RequestParam(required = false) String cat,
       @RequestParam(required = false) String sousCat,
       @RequestParam(required = false) String d1,
       @RequestParam(required = false) String d2,
       @RequestParam(required = false) String etat,
       HttpServletRequest request
       )
    {
    	TokenFilter filtre = new TokenFilter();
        
    	//Optional<TokenFront> otok = (Optional<TokenFront>)request.getAttribute("token");
        TokenFront tok = filtre.doFilter(request);
        
        Utilisateur u = uService.getUtilisateurById(tok.getIdUtilisateur());
        List val =  signService.rechercheSignalementFront(u.getRegion(),cat,sousCat,d1,d2,etat);
        
        return val;
    }
    
    @GetMapping("/{signalementId}")
    public HashMap<String,Object> ficheSignalement(
    		@PathVariable("signalementId") String idSignalement)
    {
    	return signService.getFicheSignalement(idSignalement);
    }

	@GetMapping("/region/{regionId}")
	    public List<HashMap<String, Object>> signalementsRegion(@PathVariable("regionId") String idRegion) {
	    	return signService.getSignalementsByRegion(idRegion);
	    }
	
	@GetMapping("/{idUtilisateur}")
    public List<HashMap<String, Object>> signalementsUtilisateurRegion(@PathVariable("idUtilisateur") String idUtilisateur) {
    	return signService.getSignalementsByUtilisateur(idUtilisateur);
    }
		 
		
		@PutMapping
		public String ajouterAUnGroupement(
				@PathVariable("idGroupement")
				@RequestParam(required = false)
				String idGroupement,
				@PathVariable("liste")
				@RequestParam(required = false)
				String liste) throws JsonMappingException, JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			String[] li = mapper.readValue(liste,String[].class);
			for(String s :li)
			{
				System.out.println(s);
			}
			signService.updateGroupementSignalement(li,idGroupement);
			return idGroupement;
		}
    
}
