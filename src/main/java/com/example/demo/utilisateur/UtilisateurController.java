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

    @PostMapping("/insert")
    public @ResponseBody
    void insertWithQuery(
            Utilisateur u) {
        System.out.println(u.getEmail());
        System.out.println(u.getMdp());
        uService.insertWithQuery(u);
    }

    @GetMapping("/formulaireInsert")
    public ModelAndView formulaireInsert(Model model) {
        model.addAttribute("maPage", "insertUtil");
        return new ModelAndView("template");
    }

    @GetMapping("/listeUtilisateur")
    public ModelAndView listeUtil(Model model) {
        model.addAttribute("maPage", "listUtil");
        model.addAttribute("utilisateurs", uService.getUtilisateurs());
        return new ModelAndView("template");
    }
    
    @GetMapping("/updateUtil")
    public ModelAndView updateUtil(Model model,@RequestParam(required = true) String email) {
        model.addAttribute("maPage", "editUtil");
        model.addAttribute("utilisateur", uService.getUtilisateurByEmail(email));
        return new ModelAndView("template");
    }
    
    @PutMapping(path = "{idUtilisateur}")
        public void updateUtilisateur(
           @PathVariable("idUtilisateur") String idUtil,
           @RequestParam(required = false) String email,
           @RequestParam(required = false) String mdp)
        {
            uService.updateUtil(idUtil,email,mdp);
        }
        
    @DeleteMapping(path = "{idUtilisateur}")
        public void deleteUtilisateur(
           @PathVariable("idUtilisateur") String idUtil)
        {
            uService.deleteUtil(idUtil);
        }

}
