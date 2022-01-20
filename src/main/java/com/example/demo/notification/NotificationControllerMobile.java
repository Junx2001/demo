/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.notification;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ratsi
 */
@RestController
@RequestMapping(path = "/mobile/notification")
public class NotificationControllerMobile {

   
    private final NotificationService nService;

    @Autowired
    public NotificationControllerMobile(NotificationService nService) {
        this.nService = nService;
    }

    @GetMapping(path = "{util}")
    public List<Notification> getAllNotif(@PathVariable("util") String utilisateur) {
        return nService.findByUtil(utilisateur);
    }
    
    @GetMapping(path = "/groupement/{idGroupement}")
    public void sendNotification(@PathVariable("idGroupement") String idGroupement) {
    	nService.insertNotification(idGroupement);
       // return nService.findByUtil(utilisateur);
    }
    
    

}
