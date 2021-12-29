package com.example.demo.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(path = "/regions")
public class RegionController {
	 private final RegionService regionService;

	    @Autowired
	    public RegionController(RegionService regionService) {
	        this.regionService = regionService;
	    }
	    
	    @GetMapping
	    public ModelAndView getRegions(Model model){
	    	model.addAttribute("regions", regionService.getRegions());
	    	model.addAttribute("maPage", "formulaireAffectation");
	        return new ModelAndView("template");
	    }
	    
	    
	    
}
