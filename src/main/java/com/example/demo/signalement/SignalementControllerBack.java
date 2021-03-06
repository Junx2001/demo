package com.example.demo.signalement;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.region.RegionService;



@RestController
@RequestMapping(path = "/back/signalements")
public class SignalementControllerBack  {
	
		@Autowired
		private  SignalementService signService;
		
		public SignalementControllerBack(SignalementService signService) {
		    this.signService = signService;
		}
		 
		@Autowired
		private  RegionService regionService;

	    @GetMapping
	    public ModelAndView getSignalement(Model model){
	    	
	    	model.addAttribute("signalements", signService.getSignalements());
	    	model.addAttribute("maPage", "mainTable");
	        return new ModelAndView("template");
	    }
            
        @PutMapping(path = "/{signalementId}")
        public void updateSignalement(
           @PathVariable("signalementId") String signalementId,
           @RequestParam(required = false) String region)
        {
            signService.updateSignalement(signalementId,region);
        }
	    
	    @GetMapping("/{signalementId}")
	    public ModelAndView ficheSignalement(Model model, @PathVariable("signalementId") String idSignalement) {
	    	model.addAttribute("signalement", signService.getFicheSignalement(idSignalement));
	    	model.addAttribute("regions", regionService.getRegions());
	    	model.addAttribute("maPage", "ficheSignalement2");
	        return new ModelAndView("template");
	    }
	    
	    @GetMapping("/statistique")
	    public ModelAndView statistique(Model model) {
	    	model.addAttribute("statRegion", signService.getStatParRegion());
	    	model.addAttribute("statSousCat", signService.getStatSousCategorie());
	    	model.addAttribute("regions",regionService.getRegions());
	    	model.addAttribute("maPage", "statistique");
	        return new ModelAndView("template");
	    }
            
            @GetMapping("/statParMois/{annee}")
            public List<HashMap<String,Object>> statParMois( @PathVariable("annee") Integer annee){
                return signService.getStatParMois(annee);
            }
            
            @GetMapping("/statParMoisParRegion/{idRegion}")
            public List<List<HashMap<String,Object>>> statParMoisParRegion(  @PathVariable("idRegion") String idRegion){
                List<List<HashMap<String,Object>>> liste = new ArrayList<List<HashMap<String,Object>>>();
                liste.add(signService.getResoluParMoisParRegion(idRegion));
                liste.add(signService.getSignalementsParMoisParRegion(idRegion));
                return liste;
            }
            
            
            @GetMapping("/recherche")
            public ModelAndView rechercheSignalement(Model model,
               @RequestParam(required = true) String d1,
               @RequestParam(required = true) String d2
               )
            {
                model.addAttribute("listeRecherche", signService.rechercheSignalement(d1,d2));
                model.addAttribute("maPage", "resultatRecherche");
                return new ModelAndView("template");
            }
            
	    @GetMapping("/carte")
	    public ModelAndView statistiqueSignalementParRegion(Model model) {
	    	model.addAttribute("signalements", signService.getSignalementSansRegion());
	    	model.addAttribute("regions", regionService.getRegions());
	    	model.addAttribute("maPage", "chartRegion");
	        return new ModelAndView("template");
	    }
	    
	    
	    
	    
	    
}
