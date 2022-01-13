package com.example.demo.userFinal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.utilisateur.Utilisateur;


@Service
public class UserFinalService {
	private final UserFinalRepository repository;
	
	@Autowired
	public UserFinalService(UserFinalRepository repository) {
		this.repository = repository;
	}

}
