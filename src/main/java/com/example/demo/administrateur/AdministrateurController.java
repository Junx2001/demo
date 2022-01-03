/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.administrateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ratsi
 */
@RestController
@RequestMapping(path = "/administrateur")
public class AdministrateurController {
    	@Autowired
		private  AdministrateurService adService;
		public AdministrateurController(AdministrateurService adService) {
		        this.adService = adService;
		    }
		 
		

	    @PostMapping("/login")
            public @ResponseBody Administrateur login(
               Administrateur adm
               )
            {
                /*Administrateur adm = adService.findByEmailAndMdp(email,mdp);
                if(adm!=null)
                {
                    model.addAttribute("administrateur", adm);
                    model.addAttribute("maPage", "mainTable");
                    return new ModelAndView("template");
                }
                else
                {
                    model.addAttribute("erreur", "Verifier votre Email / Mot de Passe ");
                    return new ModelAndView("login");
                }*/
                return adService.find(adm);
                
            }
	    
}
