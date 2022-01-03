/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.administrateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ratsi
 */
@Service
public class AdministrateurService {
   private final AdministrateurRepository admRepository;

    @Autowired
    public AdministrateurService(AdministrateurRepository admRepository) {
        this.admRepository = admRepository;
    }

    Administrateur find(Administrateur adm) {
       Administrateur ad = admRepository.findAdministrateurByEmailAndMdp(adm.getEmail(), adm.getMdp());
       return ad;
    }
    
}
