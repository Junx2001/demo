/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.notification;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.filter.TokenMobileFilter;
import com.example.demo.tokenMobile.TokenMobileService;

@CrossOrigin
@RestController
@RequestMapping(path = "/mobile/notifications")
public class NotificationControllerMobile {
	@Autowired
	private  TokenMobileService tserv;
   
    private final NotificationService nService;

    @Autowired
    public NotificationControllerMobile(NotificationService nService) {
        this.nService = nService;
    } 

    @GetMapping(path = "/{util}")
    public List<Notification> getAllNotif(@PathVariable("util") String utilisateur,
    		HttpServletRequest request) {
    	TokenMobileFilter filtre = new TokenMobileFilter(tserv);
        filtre.doFilter(request);
        return nService.findByUtil(utilisateur);
    }
    
    @PostMapping
    public @ResponseBody void sendNotification(String idGroupement, HttpServletRequest request) {
    	TokenMobileFilter filtre = new TokenMobileFilter(tserv);
        filtre.doFilter(request);
    	nService.insertNotification(idGroupement);
    }
    
    @PutMapping(path = "/{notif}")
    public void readNotification(@PathVariable("notif") String notif) {
    	System.out.println("update");
    	nService.readnotification(notif);
    }
    
    

}
