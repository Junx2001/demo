package com.example.demo.signalement;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping(path = "/front/signalement")
public class SignalementControllerFront  {
	
		@Autowired
		private  SignalementService signService;
		
		public SignalementControllerFront(SignalementService signService) {
		    this.signService = signService;
		}
		
	    
	    @GetMapping("/{regionId}")
	    public List<HashMap<String, Object>> signalementsRegion(Model model, @PathVariable("regionId") String idRegion) {
	    	return signService.getSignalementsByRegion(idRegion);
	    }
		 
		
		@PutMapping("/ajout/")
		public String ajouterAUnGroupement(Model model,
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
