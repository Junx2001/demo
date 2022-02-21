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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	private  TokenFrontService tserv;
	
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
    	TokenFilter filtre = new TokenFilter(tserv);
        TokenFront tok = filtre.doFilter(request);
        
        Utilisateur u = uService.getUtilisateurById(tok.getIdUtilisateur());
        List val =  signService.rechercheSignalementFront(u.getRegion(),cat,sousCat,d1,d2,etat);
        
        return val;
    }
    
    @GetMapping("/{signalementId}")
    public HashMap<String,Object> ficheSignalement(
    		@PathVariable("signalementId") String idSignalement,HttpServletRequest request)
    {
    	TokenFilter filtre = new TokenFilter(tserv);
        filtre.doFilter(request);
    	return signService.getFicheSignalement(idSignalement);
    }

	@GetMapping("/region/{regionId}")
	    public List<HashMap<String, Object>> signalementsRegion(@PathVariable("regionId") String idRegion,HttpServletRequest request) {
			TokenFilter filtre = new TokenFilter(tserv);
	        filtre.doFilter(request);
			return signService.getSignalementsByRegion(idRegion);
	    }
	
	@GetMapping("/{idUtilisateur}")
    public List<HashMap<String, Object>> signalementsUtilisateurRegion(@PathVariable("idUtilisateur") String idUtilisateur,HttpServletRequest request) {
		TokenFilter filtre = new TokenFilter(tserv);
        filtre.doFilter(request);
		return signService.getSignalementsByUtilisateur(idUtilisateur);
    }
		 
		
		@PutMapping
		public  String ajouterAUnGroupement(
				@RequestBody(required = false)
				HashMap jobj ,
				HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
			
			TokenFilter filtre = new TokenFilter(tserv);
	        filtre.doFilter(request);
	        
	        String idGroupement = (String)jobj.get("idGroupement");
	        
	        List<String> li = (List<String>)jobj.get("liste");
			for(String s :li)
			{
				System.out.println(s);
			}
			signService.updateGroupementSignalement(li,idGroupement);
			return idGroupement;
		}
    
}
