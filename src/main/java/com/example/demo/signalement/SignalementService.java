package com.example.demo.signalement;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	public HashMap<String, Object> getFicheSignalement(String idSignalement){
		HashMap<String, Object> hm = new HashMap<String, Object>();
		List<Object[]> liste = signRepository.getFicheSignalement(idSignalement);
		Object[] s = (Object[])liste.get(0);
		hm.put("idSignalement", s[0]);
		hm.put("dateSignalement", s[1]);
		hm.put("description", s[2]);
		hm.put("latitude", s[3]);
		hm.put("longitude", s[4]);
		hm.put("nomImage", s[5]);
		hm.put("region", s[6]);
		hm.put("nomSousCat", s[7]);
		hm.put("nomCat", s[8]);
		hm.put("email", s[9]);
		return hm;
		
	}
	
	
	public List<HashMap<String, Object>> getStatParRegion(){
		List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
		List<Object[]> liste =   signRepository.getStatParRegion();
		for(int i=0; i<liste.size(); i++) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			Object[] s = (Object[]) liste.get(i);
			hm.put("region", s[0]);
			hm.put("nb", s[1]);
			listehm.add(hm);
		}
		return listehm;
		
	}
	
	public List<HashMap<String, Object>> getStatSousCategorie(){
		List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
		List<Object[]> liste =   signRepository.getStatSousCategorie();
		for(int i=0; i<liste.size(); i++) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			Object[] s = (Object[]) liste.get(i);
			hm.put("label", s[0]);
			hm.put("nb", s[1]);
			listehm.add(hm);
		}
		return listehm;
		
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
