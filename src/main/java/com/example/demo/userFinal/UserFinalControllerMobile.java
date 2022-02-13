package com.example.demo.userFinal;

import com.example.demo.tokenMobile.TokenMobileService;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/mobile/userFinaux")
public class UserFinalControllerMobile {
	private final UserFinalService service;
	
        @Autowired
	 private TokenMobileService tokService;
	@Autowired
	public UserFinalControllerMobile(UserFinalService service) {
		this.service = service;
	}
	
    @PostMapping("/login")
    public @ResponseBody String login(UserFinal util)
    {
    	Optional<UserFinal> val = null;
        val =  service.find(util);
        String tok = tokService.insertToken(val.get().getIdUserFinal());        
        return "L'utilisateur Final avec l'email "+val.get().getEmail()+" s'est connecté\n Il obtient un token d'authentification : "+tok;
    }
	
    @PostMapping
    public @ResponseBody String insertWithQuery(UserFinal u) {
        System.out.println(u.getEmail());
        System.out.println(u.getMdp());
        service.insertWithQuery(u);
        return "L'utilisateur Final avec l'email "+u.getEmail()+" a été inséré";
    }
	
	
}