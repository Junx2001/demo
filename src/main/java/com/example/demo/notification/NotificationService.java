/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.notification;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.demo.signalement.Signalement;
import com.example.demo.signalement.SignalementService;

@Service
public class NotificationService {
    private final NotificationRepository nRepository;
    
    @Autowired
    public NotificationService(NotificationRepository nRepository) {
        this.nRepository = nRepository;
    }
    
    @Autowired
    private SignalementService singnServ;
   
   

    public List<Notification> findAll() {      
        return nRepository.findAll();
    }

    List<Notification> findByUtil(String utilisateur) {
        return nRepository.findByUtilisateur(utilisateur);
    }
    
    @Transactional
	public void insertNotification(String idGroupement) {
    	List<HashMap<String, Object>> liste= singnServ.getSignalementsByGroupement(idGroupement);
    	//System.out.print(liste.size());
    	for(int i=0;i<liste.size();i++) {
    		HashMap<String, Object> signal=liste.get(i);
    		System.out.println("signal : "+signal);
    		Notification n=new Notification();
    		n.setUtilisateur(signal.get("id_utilisateur").toString());
    		String msg="Grace a votre signalement du "+signal.get("date_signalement").toString()+" concernant "+signal.get("description").toString()+
    				", on a pu identifier le probleme et entamer les demarches necessaires pour l'amelioration du quotidient des malgaches \n"
    				+ "La resolution du probleme que vous avez signaler s'est terminée le "+signal.get("date_resolu").toString()+".\n"
    						+ "Nous vous remercions de votre participation a cette initiative de developpement pour un madagascar en paix";
    		n.setMessage(msg);
    		n.setDescription((String)signal.get("description"));
    		n.setNomImage((String)signal.get("nom_image"));
    		System.out.println("image :"+n.getNomImage());
    		n.setDateHeure(LocalDateTime.now());
    		nRepository.save(n);
    	}
    }
    
    @Transactional
    void readnotifivcation(String idNotification) {
    	//System.out.print(liste.size());
    	Optional<Notification> not=this.nRepository.findById(idNotification);
    	Notification n=not.get();
		n.setEtat("Lu");
    	this.nRepository.save(n);
    }
    
}
