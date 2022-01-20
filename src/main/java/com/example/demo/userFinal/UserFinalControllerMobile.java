package com.example.demo.userFinal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = "/mobile/userFinal")
public class UserFinalControllerMobile {
	private final UserFinalService service;
	
	@Autowired
	public UserFinalControllerMobile(UserFinalService service) {
		this.service = service;
	}
	
	@PostMapping("/login")
    public @ResponseBody Optional<UserFinal> login(UserFinal util)
    {
    	Optional<UserFinal> val = null;
        try {
			val =  service.find(util);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return val;
    }
	
	@PostMapping
    public @ResponseBody void insertWithQuery(UserFinal u) {
        System.out.println(u.getEmail());
        System.out.println(u.getMdp());
        service.insertWithQuery(u);
    }
	
	
}