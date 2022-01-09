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

import com.example.demo.signalement.SignalementService;

/**
 *
 * @author ratsi
 */
@RestController
@RequestMapping(path = "/utilisateur")
public class UtilisateurController {
    	@Autowired
		private  UtilisateurService uService;
		public UtilisateurController(UtilisateurService uService) {
		        this.uService = uService;
		    }
		
		@Autowired
		private  SignalementService signService;
		 
		 @PostMapping("/insert")
		 public @ResponseBody void insertWithQuery(
            Utilisateur u){
                     System.out.println(u.getEmail());
                     System.out.println(u.getMdp());
		   	 uService.insertWithQuery(u);
		 }
                 
                @GetMapping("/formulaireInsert")
		 public ModelAndView formulaireInsert(Model model){
                    	model.addAttribute("maPage", "insertUtil");
                        return new ModelAndView("template");
		 }

	  
}
