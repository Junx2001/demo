package com.example.demo.signalement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
	
	public List<Object[]> getFicheSignalement(String idSignalement){
		return signRepository.getFicheSignalement(idSignalement);
	}
	

	public List<Object[]> getDetailsSignalements(){
		return signRepository.getDetailsSignalements();
	}
	
	public List<Object[]> getStatParRegion(){
		return signRepository.getStatParRegion();
	}

    @Transactional
    void updateSignalement(String signalementId, String region) {
        Signalement sign = signRepository.findById(signalementId)
                .orElseThrow(() -> new IllegalStateException(
                "signalement with id " + signalementId + " does not exists"));
        if (region != null && region.length() > 0 && sign.getRegion() == null) {
            sign.setRegion(region);
            signRepository.save(sign);
        }

    }

}
