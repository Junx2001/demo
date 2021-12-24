package com.example.demo.signalement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/signalement")
public class SignalementController {
	 private final SignalementService signService;

     
	    @Autowired
	    public SignalementController(SignalementService signService) {
	        this.signService = signService;
	    }
	    
	    @GetMapping
	    public List<Signalement> getStudents(){
	        return signService.getSignalements();
	    }
}
