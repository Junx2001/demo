package com.example.demo.categorie;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CategorieRepository 
	extends JpaRepository<Categorie,String>{
	
	
	Optional<Categorie> findCategorieByLabel(String label);
	
}
