/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.utilisateur;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.region.RegionService;
import com.example.demo.signalement.SignalementService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author ratsi
 */
@RestController
@RequestMapping(path = "/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService uService;

    public UtilisateurController(UtilisateurService uService) {
        this.uService = uService;
    }

    @Autowired
    private SignalementService signService;
    
    @Autowired
    private RegionService regService;

    @PostMapping
    public @ResponseBody ModelAndView insertWithQuery(Model model, Utilisateur u) {
        System.out.println(u.getEmail());
        System.out.println(u.getMdp());
        System.out.println(u.getRegion());
        try {
        	 uService.insertWithQuery(u);
        	 model.addAttribute("succes", "L'utilisateur a été inséré");
        }catch(IllegalStateException ex) {
        	model.addAttribute("erreur",ex.getMessage());
        }
        model.addAttribute("regions",regService.getRegions());
        model.addAttribute("maPage", "insertUtil");
       
		return new ModelAndView("template");
    }

    @GetMapping("/formulaireInsert")
    public ModelAndView formulaireInsert(Model model) {
    	model.addAttribute("regions",regService.getRegions());
        model.addAttribute("maPage", "insertUtil");
        return new ModelAndView("template");
    }

    @GetMapping("/listeUtilisateur")
    public ModelAndView listeUtil(Model model) {
        model.addAttribute("maPage", "listUtil");
        model.addAttribute("utilisateurs", uService.getUtilisateurs());
        return new ModelAndView("template");
    }
    
    @GetMapping("/formulaireUpdate")
    public ModelAndView updateUtil(Model model,@RequestParam(required = true) String idUtil,@RequestParam(required = false) String reponse) {
        model.addAttribute("maPage", "editUtil");
        model.addAttribute("regions",regService.getRegions());
        model.addAttribute("utilisateur", uService.getUtilisateurById(idUtil));
        if (reponse!=null) {
        	model.addAttribute("succes", reponse);
        }
        return new ModelAndView("template");
    }
    
    @PutMapping(path = "{idUtilisateur}")
        public void updateUtilisateur(
           @PathVariable("idUtilisateur") String idUtil,
           @RequestParam(required = false) String email,
           @RequestParam(required = false) String mdp,
           @RequestParam(required = false) String idRegion)
        {
    		if (email.compareTo("")==0) email=null;
    		if (mdp.compareTo("")==0) mdp=null;
    		if (idRegion.compareTo("")==0) idRegion=null;
            uService.updateUtil(idUtil,email,mdp,idRegion);
        }
        
    @DeleteMapping(path = "{idUtilisateur}")
        public void deleteUtilisateur(
           @PathVariable("idUtilisateur") String idUtil)
        {
            uService.deleteUtil(idUtil);
        }

}
