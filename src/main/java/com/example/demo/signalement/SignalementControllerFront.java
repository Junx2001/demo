package com.example.demo.signalement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/front/signalement")
public class SignalementControllerFront {
	@Autowired
	private  SignalementService signService;
	
	public SignalementControllerFront(SignalementService signService) {
	    this.signService = signService;
	}
	
    @GetMapping
    public List rechercheSignalementFront(
       @RequestParam(required = false) String cat,
       @RequestParam(required = false) String sousCat,
       @RequestParam(required = false) String d1,
       @RequestParam(required = false) String d2,
       @RequestParam(required = false) String etat
       )
    {
    	
        return signService.rechercheSignalementFront(cat,sousCat,d1,d2,etat);
    }

}
