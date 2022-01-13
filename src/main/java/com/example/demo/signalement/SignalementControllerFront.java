package com.example.demo.signalement;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
		 
		
		@PutMapping
		public void ajouterAUnGroupement(Model model, 
				@PathVariable("listeSignalement") List<String> listeSignalement,
				@PathVariable("idGroupement") String idGroupement) {
			//return signService.getSignalementsByRegion(idRegion);
			 signService.updateGroupementSignalement(listeSignalement,idGroupement);
		}
}
