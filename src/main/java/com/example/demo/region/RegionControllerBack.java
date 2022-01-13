package com.example.demo.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(path = "/back/regions")
public class RegionControllerBack {
	 private final RegionService regionService;

	    @Autowired
	    public RegionControllerBack(RegionService regionService) {
	        this.regionService = regionService;
	    }
	    
	    @GetMapping
	    public ModelAndView getRegions(Model model){
	    	model.addAttribute("regions", regionService.getRegions());
	    	model.addAttribute("maPage", "formulaireAffectation");
	        return new ModelAndView("template");
	    }
	    
	    
	    
}
