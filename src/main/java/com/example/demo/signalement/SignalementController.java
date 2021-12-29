package com.example.demo.signalement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	    
	    
	    
}
