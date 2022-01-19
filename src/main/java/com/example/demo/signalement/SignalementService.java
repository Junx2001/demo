package com.example.demo.signalement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
    public List<HashMap<String,Object>> hashMapSignalement(List<Object[]> liste){
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
             hm.put("idRegion", s[10]);
             hm.put("etat", s[11]);
             hm.put("idUserFinal", s[12]);
             hm.put("dateHeureSignalement", s[1]);
             listehm.add(hm);
         }
         return listehm;
    }
    
    public List<HashMap<String, Object>> getSignalements() {
    	 List<Object[]> liste = signRepository.getDetailsSignalements();
    	 List<HashMap<String, Object>> listehm =this.hashMapSignalement(liste);
         return listehm;
    }

    public HashMap<String, Object> getFicheSignalement(String idSignalement) {
        List<Object[]> liste = signRepository.getFicheSignalement(idSignalement);
        List<HashMap<String, Object>> listehm =this.hashMapSignalement(liste);
        return listehm.get(0);
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
    
    @Transactional
	public void updateGroupementSignalement(List<String> listeSignalementId, String groupemetId) {
    	for(String signalementId : listeSignalementId) {
    		Signalement sign = signRepository.findById(signalementId)
                    .orElseThrow(() -> new IllegalStateException(
                    "signalement with id " + signalementId + " does not exists"));
            if (groupemetId != null && groupemetId.length() > 0 && sign.getIdGroupement() == null) {
                sign.setIdGroupement(groupemetId);
                signRepository.save(sign);
            }
    	}
    }
    
    public List<HashMap<String, Object>> getSignalementSansRegion(){
		List<Object[]> liste =   signRepository.getStatSignalementSansRegion();
		List<HashMap<String, Object>> listehm = new ArrayList<HashMap<String, Object>>();
		for(int i=0; i<liste.size(); i++) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			Object[] s = (Object[]) liste.get(i);
			hm.put("idSignalement", s[0]);
             hm.put("dateSignalement", s[1]);
			hm.put("description", s[2]);
			hm.put("idGroupement", s[3]);
			hm.put("idSousCategorie", s[4]);
			hm.put("latitude", s[5]);
			hm.put("longitude", s[6]);
			hm.put("nomImage", s[7]);
			hm.put("region", s[8]);
			hm.put("idUtilisateur", s[9]);
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
        List<Object[]> liste = signRepository.rechercheSign(d1, d2);
        List<HashMap<String, Object>> listehm =this.hashMapSignalement(liste);
        return listehm;
    }
    
    public List<HashMap<String, Object>> getSignalementsByRegion(String idRegion) {
      	 List<Object[]> liste = signRepository.getSignalementByRegion(idRegion);
      	 List<HashMap<String, Object>> listehm =this.hashMapSignalement(liste);
        return listehm;
      }

    public List rechercheSignalementFront(String cat, String sousCat, String d1, String d2, String etat) {
        String sql = "SELECT * FROM detailsSignalement WHERE idSignalement is not null ";
        
        if(cat!=null)
        {
        	sql+=" AND ";
            sql += "nomCat = '"+cat+"'";
        }
        if(sousCat!=null)
        {
             sql+=" AND ";  
            sql += "sousCat = '"+sousCat+"'";
        }
        if(d1!=null)
        {
        	sql+=" AND ";
            sql += "dateSignalement >= '"+d1+"'";
        }
        if(d2!=null)
        {
        	sql+=" AND ";
            sql += "dateSignalement <= '"+d2+"'";
        }
            if(etat!=null)
        {
            sql+=" AND ";
            sql += "etat = "+etat;
        }
        List<Object[]> liste = entityManager.createNativeQuery(sql).getResultList();
          
        List<HashMap<String, Object>> listehm =this.hashMapSignalement(liste);
        return listehm;
    }
    
    public boolean verifDate(String date1,String date2) throws ParseException {
    	Date dateOne=new SimpleDateFormat("yyyy/MM/dd").parse(date1);  
    	Date dateTwo=new SimpleDateFormat("yyyy/MM/dd").parse(date2); 
    	return dateTwo.after(dateOne);
    }

}
