package com.example.demo.signalement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SignalementService {
	private final SignalementRepository signRepository;

    @Autowired
    public SignalementService(SignalementRepository signRepository) {
        this.signRepository = signRepository;
    }
    
	public List<Signalement> getSignalements() {
		return signRepository.findAll();
	}
	
	public List<Object[]> getFicheSignalements(){
		return signRepository.getFicheSignalements();
	}

}
