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


@RestController
@RequestMapping(path = "/signalement")
public class SignalementController {
	 private final SignalementService signService;

     
	    @Autowired
	    public SignalementController(SignalementService signService) {
	        this.signService = signService;
	    }
	    
	    @GetMapping
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
	    
	    @GetMapping("/fiche")
	    public List<Object[]> ficheSignalement() {
	    	return signService.getFicheSignalements();
	    	/*model.addAttribute("signalements", signService.getFicheSignalements());
	    	model.addAttribute("maPage", "affectation");
	        return new ModelAndView("template");*/
	    }
	    
	    
	    
}
