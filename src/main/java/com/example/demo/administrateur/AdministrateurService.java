/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.administrateur;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.fonction.Fonction;


@Service
public class AdministrateurService {
   private final AdministrateurRepository admRepository;

   
    @Autowired
    public AdministrateurService(AdministrateurRepository admRepository) {
        this.admRepository = admRepository;
    }

    public Optional<Administrateur> find(Administrateur adm) {      
    	if (!Fonction.verifEmail(adm.getEmail())) {
    		throw new IllegalStateException("La syntaxe de votre email est incorrect");
		 }
    	if (!Fonction.verifMdp(adm.getMdp())) {
    		throw new IllegalStateException("Votre mot de passe doit contenir 8 caratères et aucun accent");
		}
    	
    	Optional<Administrateur> ad = admRepository.findAdministrateurByEmailAndMdp(adm.getEmail(),adm.getMdp());
    	if (!ad.isPresent()) {
			throw new IllegalStateException("Veuillez verifier votre mot de passe et votre email");
		}
        return ad;
    }
    
}
