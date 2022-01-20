/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.notification;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ratsi
 */
@Service
public class NotificationService {
    private final NotificationRepository nRepository;

   
    @Autowired
    public NotificationService(NotificationRepository nRepository) {
        this.nRepository = nRepository;
    }

    public List<Notification> findAll() {      
        return nRepository.findAll();
    }

    List<Notification> findByUtil(String utilisateur) {
        return nRepository.findByUtilisateur(utilisateur);
    }
}
