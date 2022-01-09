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
@RequestMapping(path = "/sousCategorie")
public class SousCategorieController {
	private final SousCategorieService service;
	
	@Autowired
	public SousCategorieController(SousCategorieService service) {
		this.service = service;
	}
	@Autowired
	private CategorieService catService;
	
	@PostMapping
    public @ResponseBody ModelAndView registerNewCategorie(SousCategorie sousCat,Model model){
		service.insertWithQuery(sousCat);
		
		List<Categorie> listCat = catService.getCategories();	
		List<List<SousCategorie>> listeSousCat = new ArrayList<List<SousCategorie>>();
		for(int i=0; i<listCat.size(); i++) {
			Categorie temp = (Categorie)listCat.get(i);
			listeSousCat.add(service.getSousCatByIdCat(temp));
		}
    	model.addAttribute("categories", catService.getCategories());
    	model.addAttribute("sousCategories", listeSousCat);
    	model.addAttribute("maPage", "listeCategories");
    	model.addAttribute("insertionSousCat","Le sous-catégorie "+sousCat.getLabel()+" a été ajouté");
        return new ModelAndView("template");
    }
	
	@DeleteMapping(path = "{sousCategorieId}")
    public String deleteSousCategorie(@PathVariable("sousCategorieId") String sousCategorieId)
    {
        service.deleteSousCategorie(sousCategorieId);
        return "Le sous-catégorie a été supprimé";
    }
}
