package com.example.demo.userFinal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.utilisateur.Utilisateur;

@RestController
@RequestMapping(path = "/userFinal")
public class UserFinalController {
	private final UserFinalService service;
	
	@Autowired
	public UserFinalController(UserFinalService service) {
		this.service = service;
	}
	
	/*@PostMapping("/login")
    public @ResponseBody Optional<UserFinal> login(UserFinal util)
    {
    	Optional<UserFinal> val = null;
        try {
			val =  service.loginUserFinal(util);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return val;
    }*/
	
	
}
