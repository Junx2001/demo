package com.example.demo.signalement;

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
@RequestMapping(path = "/signalement")
public class SignalementController {
		@Autowired
		private  SignalementService signService;
		public SignalementController(SignalementService signService) {
		        this.signService = signService;
		    }
		 
		@Autowired
		private  RegionService regionService;

	    @GetMapping()
	    public ModelAndView getSignalement(Model model){
	    	model.addAttribute("signalements", signService.getSignalements());
	    	model.addAttribute("maPage", "mainTable");
	        return new ModelAndView("template");
	    }
            
        @PutMapping(path = "{signalementId}")
        public void updateSignalement(
           @PathVariable("signalementId") String signalementId,
           @RequestParam(required = false) String region)
        {
            signService.updateSignalement(signalementId,region);
        }
	    
	    @GetMapping("/{signalementId}")
	    public ModelAndView ficheSignalement(Model model, @PathVariable("signalementId") String idSignalement) {
	    	//return signService.getFicheSignalement(idSignalement);
	    	model.addAttribute("signalement", signService.getFicheSignalement(idSignalement));
	    	model.addAttribute("regions", regionService.getRegions());
	    	model.addAttribute("maPage", "ficheSignalement");
	        return new ModelAndView("template");
	    }
	    
	    
	    
}
