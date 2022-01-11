package com.example.demo.sousCategorie;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.categorie.Categorie;


@Repository
public interface SousCategorieRepository 
	extends JpaRepository<SousCategorie,String>{
	
	List<SousCategorie> findSousCategorieByIdCategorie(String idCategorie);

	Optional<Categorie> findSousCategorieByLabel(String label);
	boolean existsByLabel(String label);
}
