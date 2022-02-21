package com.example.demo.sousCategorie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping(path = "/front/sousCategories")
public class SousCategorieControllerFront {
private final SousCategorieService service;
	
	@Autowired
	public SousCategorieControllerFront(SousCategorieService service) {
		this.service = service;
	}
	
	@GetMapping
    public List<SousCategorie> getSousCategories(){
		return service.getAll();	
    }

}
