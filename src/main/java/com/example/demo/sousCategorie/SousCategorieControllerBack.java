package com.example.demo.sousCategorie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.categorie.Categorie;
import com.example.demo.categorie.CategorieService;


@RestController
@RequestMapping(path = "/back/sousCategories")
public class SousCategorieControllerBack {
	private final SousCategorieService service;
	
	@Autowired
	public SousCategorieControllerBack(SousCategorieService service) {
		this.service = service;
	}
	@Autowired
	private CategorieService catService;
	
	@PostMapping
    public @ResponseBody ModelAndView registerNewCategorie(SousCategorie sousCat,Model model){
		try {
			service.insertWithQuery(sousCat);
			model.addAttribute("insertionSousCat","Le sous-catégorie "+sousCat.getLabel()+" a été ajouté");
		}catch(IllegalStateException ex) {
			model.addAttribute("exception", ex.getMessage());
		}
		
		
		List<Categorie> listCat = catService.getCategories();	
		List<List<SousCategorie>> listeSousCat = new ArrayList<List<SousCategorie>>();
		for(int i=0; i<listCat.size(); i++) {
			Categorie temp = (Categorie)listCat.get(i);
			listeSousCat.add(service.getSousCatByIdCat(temp));
		}
    	model.addAttribute("categories", catService.getCategories());
    	model.addAttribute("sousCategories", listeSousCat);
    	model.addAttribute("maPage", "listeCategories");
    	
        return new ModelAndView("template");
    }
	
	@DeleteMapping(path = "/{sousCategorieId}")
    public String deleteSousCategorie(@PathVariable("sousCategorieId") String sousCategorieId)
    {
		try {
	        service.deleteSousCategorie(sousCategorieId);
	        return "Le sous-catégorie a été supprimé";
		}catch(IllegalStateException ex) {
			return ex.getMessage();
		}
    }
}
