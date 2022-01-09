package com.example.demo.categorie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.sousCategorie.SousCategorie;
import com.example.demo.sousCategorie.SousCategorieService;

@RestController
@RequestMapping(path = "/categorie")
public class CategorieController {
	
	private final CategorieService service;
	
	@Autowired
	public CategorieController(CategorieService service) {
		this.service = service;
	}
	

	@Autowired
	private SousCategorieService sousCatService;
	
	@GetMapping
    public ModelAndView getCategories(Model model){
		List<Categorie> listCat = service.getCategories();	
		List<List<SousCategorie>> listeSousCat = new ArrayList<List<SousCategorie>>();
		for(int i=0; i<listCat.size(); i++) {
			Categorie temp = (Categorie)listCat.get(i);
			listeSousCat.add(sousCatService.getSousCatByIdCat(temp));
		}
    	model.addAttribute("categories", service.getCategories());
    	model.addAttribute("sousCategories", listeSousCat);
    	model.addAttribute("maPage", "listeCategories");
        return new ModelAndView("template");
    }
	
	@PostMapping(
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
	        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
    public @ResponseBody ModelAndView registerNewCategorie( Categorie categorie,Model model){
		service.insertWithQuery(categorie);
		
		List<Categorie> listCat = service.getCategories();	
		List<List<SousCategorie>> listeSousCat = new ArrayList<List<SousCategorie>>();
		for(int i=0; i<listCat.size(); i++) {
			Categorie temp = (Categorie)listCat.get(i);
			listeSousCat.add(sousCatService.getSousCatByIdCat(temp));
		}
    	model.addAttribute("categories", service.getCategories());
    	model.addAttribute("sousCategories", listeSousCat);
    	model.addAttribute("maPage", "listeCategories");
    	model.addAttribute("insertion","La catégorie "+categorie.getLabel()+" a été ajouté");
        return new ModelAndView("template");
    }
	
	@DeleteMapping(path = "{categorieId}")
    public String deleteCategorie(@PathVariable("categorieId") String categorieId)
    {
        service.deleteCategorie(categorieId);
        return "La catégorie a été supprimé";
    }
	
}
