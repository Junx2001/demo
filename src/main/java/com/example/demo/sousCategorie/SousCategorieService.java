package com.example.demo.sousCategorie;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.categorie.Categorie;

@Service
public class SousCategorieService {
	@PersistenceContext
    private EntityManager entityManager;
	
	private final SousCategorieRepository repository;
	
	@Autowired
	public SousCategorieService(SousCategorieRepository repository) {
		this.repository = repository;
	}
	
	public List<SousCategorie> getSousCatByIdCat (Categorie cat) {
        return repository.findSousCategorieByIdCategorie(cat.getIdCategorie());
    }

	
	@Transactional
	public void insertWithQuery(SousCategorie sousCat) {
		boolean exists = repository.existsByLabel(sousCat.getLabel());
		if (exists) {
            throw new IllegalStateException(
            		  "Le sous-categorie avec le nom " + sousCat.getLabel() + " existe deja ");
        }else {
        	entityManager.createNativeQuery("INSERT INTO sous_categorie (id_sous_categorie,id_categorie, label) VALUES (CONCAT('SC',NEXT VALUE FOR seq_sous_categorie),?,?)")
	      .setParameter(1, sousCat.getIdCategorie())
	      .setParameter(2, sousCat.getLabel())
	      .executeUpdate();
        }
	}

	public void deleteSousCategorie(String sousCategorieId) {
		// TODO Auto-generated method stub
	        boolean exists = repository.existsById(sousCategorieId);
	        if (!exists) {
	            throw new IllegalStateException(
	                "Le sous categorie avec id " + sousCategorieId + " n'existe pas");
	        }
	        repository.deleteById(sousCategorieId);
	}
}
