/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.administrateur;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping(path = "/administrateur")
public class AdministrateurController {
    	@Autowired
		private  AdministrateurService adService;
		public AdministrateurController(AdministrateurService adService) {
		        this.adService = adService;
		    }
		
		@Autowired
		private  SignalementService signService;
		 
		 @GetMapping("/login")
		 public ModelAndView getSignalement(Model model){
		    return new ModelAndView("login");
		 }

	     @PostMapping("/login")
         public @ResponseBody ModelAndView login(Administrateur adm, Model model,HttpServletRequest request)
         {
                Administrateur val = adService.find(adm);
                if(val!=null)
                {
                	HttpSession session = request.getSession();
                	if (session.getAttribute("admin")==null) {
                		session.setAttribute("admin", val);
                	}
                	
                    model.addAttribute("administrateur", val);
                    model.addAttribute("signalements", signService.getSignalements());
        	    	model.addAttribute("maPage", "mainTable");
        	        return new ModelAndView("template");
                }
                else
                {
                    model.addAttribute("erreur", "Verifier votre Email / Mot de Passe ");
        		    return new ModelAndView("login");
                }
               
         }
	     
        @GetMapping("/logout")
     	public ModelAndView destroySession(HttpServletRequest request) {
     		request.getSession().invalidate();
     		System.out.println(request.getSession().getAttribute("admin"));
     		return new ModelAndView("login");
     	}
	    
}
