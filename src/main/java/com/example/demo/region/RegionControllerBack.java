package com.example.demo.region;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.signalement.SignalementService;


@RestController
@RequestMapping(path = "/back")
public class RegionControllerBack {
	 private final RegionService regionService;

	    @Autowired
	    public RegionControllerBack(RegionService regionService) {
	        this.regionService = regionService;
	    }
	    
	    @Autowired
		private  SignalementService signService;
	    
	    @GetMapping("/regions")
	    public ModelAndView getRegions(Model model){
	    	model.addAttribute("regions", regionService.getRegions());
	    	model.addAttribute("maPage", "formulaireAffectation");
	        return new ModelAndView("template");
	    }
	    
	    
	    
	    /*@GetMapping("/verifDate")
	    public boolean signalementsRegion() throws ParseException {
	    	String date1="2021/11/25";
	    	String date2="2021/11/24";
	    	return signService.verifDate(date2, date1);
	    }*/
	    
	    
}
