package com.example.demo.categorie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/front/categories")
public class CategorieControllerFront {
private final CategorieService service;
	
	@Autowired
	public CategorieControllerFront(CategorieService service) {
		this.service = service;
	}
	
	@GetMapping
    public List<Categorie> getCategories(){
		return service.getCategories();	
    }

}
