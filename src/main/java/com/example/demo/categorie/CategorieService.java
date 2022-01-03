package com.example.demo.categorie;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CategorieService {
	@PersistenceContext
    private EntityManager entityManager;
	
	private final CategorieRepository repository;
	
	@Autowired
	public CategorieService(CategorieRepository repository) {
		this.repository = repository;
	}
	
	public List<Categorie> getCategories(){
		return repository.findAll();
	}
	
	@Transactional
	public void insertWithQuery(Categorie cat) {
	    entityManager.createNativeQuery("INSERT INTO categorie (id_categorie, label) VALUES (CONCAT('C',NEXT VALUE FOR seq_categorie),?)")
	      .setParameter(1, cat.getLabel())
	      .executeUpdate();
	}

	public void deleteCategorie(String categorieId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
        boolean exists = repository.existsById(categorieId);
        if (!exists) {
            throw new IllegalStateException(
                "La categorie avec id " + categorieId + " n'existe pas");
        }
        repository.deleteById(categorieId);
	}
}
