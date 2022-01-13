package com.example.demo.signalement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignalementService {

    @PersistenceContext
    private EntityManager entityManager;
    private final SignalementRepository signRepository;

    @Autowired
    public SignalementService(SignalementRepository signRepository) {
        this.signRepository = signRepository;
    }
    

    public List<HashMap<String, Object>> getSignalements() {
    	 List<Object[]> liste = signRepository.getDetailsSignalements();
    	 List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
         for (int i = 0; i < liste.size(); i++) {
             HashMap<String, Object> hm = new HashMap<String, Object>();
             Object[] s = (Object[]) liste.get(i);
             hm.put("idSignalement", s[0]);
             String str = new SimpleDateFormat("dd-MM-yyyy").format(s[1]);
             hm.put("dateSignalement", str);
             hm.put("description", s[2]);
             hm.put("latitude", s[3]);
             hm.put("longitude", s[4]);
             hm.put("nomImage", s[5]);
             hm.put("region", s[6]);
             hm.put("nomSousCat", s[7]);
             hm.put("nomCat", s[8]);
             hm.put("email", s[9]);
             listehm.add(hm);
         }
         return listehm;
    }

    public HashMap<String, Object> getFicheSignalement(String idSignalement) {
        List<Object[]> liste = signRepository.getFicheSignalement(idSignalement);
        HashMap<String, Object> hm = new HashMap<String, Object>();
        
        Object[] s = (Object[]) liste.get(0);
        
        hm.put("idSignalement", s[0]);
        
        String str = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(s[1]);
        
        hm.put("dateSignalement", str);
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

    public List<HashMap<String, Object>> getStatParRegion() {
        List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
        List<Object[]> liste = signRepository.getStatParRegion();
        for (int i = 0; i < liste.size(); i++) {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            Object[] s = (Object[]) liste.get(i);
            hm.put("region", s[0]);
            hm.put("nb", s[1]);
            listehm.add(hm);
        }
        return listehm;

    }

    public List<HashMap<String, Object>> getStatSousCategorie() {
        List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
        List<Object[]> liste = signRepository.getStatSousCategorie();
        for (int i = 0; i < liste.size(); i++) {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            Object[] s = (Object[]) liste.get(i);
            hm.put("label", s[0]);
            hm.put("nb", s[1]);
            listehm.add(hm);
        }
        return listehm;

    }
    
	public List<HashMap<String, Object>> getStatSignalementParRegion(){
		List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
		List<Object[]> liste =   signRepository.getStatSignalementParRegion();
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
    
    public List<HashMap<String, Object>> getSignalementSansRegion(){
		List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
		List<Object[]> liste =   signRepository.getStatSignalementSansRegion();
		for(int i=0; i<liste.size(); i++) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			Object[] s = (Object[]) liste.get(i);
			hm.put("idSignalement", s[0]);
			hm.put("dateSignalement", s[1]);
			hm.put("description", s[2]);
			hm.put("idGroupement", s[3]);
			hm.put("idSousCategorie", s[4]);
			hm.put("idUtilisateur", s[5]);
			hm.put("latitude", s[6]);
			hm.put("longitude", s[7]);
			hm.put("nomImage", s[8]);
			hm.put("region", s[9]);
			listehm.add(hm);
		}
		return listehm;
		
	}

    public List<HashMap<String, Object>> getStatParMois(Integer annee) {
        List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
        List<Object[]> liste = signRepository.getStatParMois(annee);
        for (int i = 0; i < liste.size(); i++) {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            Object[] s = (Object[]) liste.get(i);
            hm.put("nb", s[0]);
            hm.put("mois", s[1]);
            listehm.add(hm);
        }
        return listehm;
    }

    public List<HashMap<String, Object>> rechercheSignalement(String d1, String d2) {
        List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
        List<Object[]> liste = signRepository.rechercheSign(d1, d2);
        for (int i = 0; i < liste.size(); i++) {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            Object[] s = (Object[]) liste.get(i);
            hm.put("idSignalement", s[0]);
            String str = new SimpleDateFormat("dd-MM-yyyy").format(s[1]);
            hm.put("dateSignalement", str);
            hm.put("description", s[2]);
            hm.put("latitude", s[3]);
            hm.put("longitude", s[4]);
            hm.put("nomImage", s[5]);
            hm.put("region", s[6]);
            hm.put("nomSousCat", s[7]);
            hm.put("nomCat", s[8]);
            hm.put("email", s[9]);
            listehm.add(hm);
        }
        return listehm;
    }

    List rechercheSignalementFront(String cat, String sousCat, String d1, String d2, String etat) {
        String sql = "SELECT * FROM detailsSignalement WHERE ";
        Boolean efaNisy = false;
        if(cat!=null)
        {
            sql += "nomCat = '"+cat+"'";
            efaNisy = true;
        }
        if(sousCat!=null)
        {
            if(efaNisy)
                sql+=" AND ";
            sql += "sousCat = '"+sousCat+"'";
            efaNisy = true;
        }
        if(d1!=null)
        {
            if(efaNisy)
                sql+=" AND ";
            sql += "dateSignalement >= '"+d1+"'";
            efaNisy = true;
        }
        if(d2!=null)
        {
            if(efaNisy)
                sql+=" AND ";
            sql += "dateSignalement <= '"+d2+"'";
            efaNisy = true;
        }
            if(sousCat!=null)
        {
            if(efaNisy)
                sql+=" AND ";
            sql += "sousCat = '"+sousCat+"'";
            efaNisy = true;
        }
        List<Object[]> list = entityManager.createNativeQuery(sql)
        .getResultList();
          
        return list;
          
          
        
    }

}
