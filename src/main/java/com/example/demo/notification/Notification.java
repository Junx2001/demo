/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.notification;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notification")
/**
 *
 * @author ratsi
 */
public class Notification {
    @Id
    private String id;
    private String utilisateur;
    private String message;
    private LocalDateTime dateHeure;
    

    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public LocalDateTime getDateHeure() {
		return dateHeure;
	}


	public void setDateHeure(LocalDateTime dateHeure) {
		this.dateHeure = dateHeure;
	}


	public Notification(String id, String utilisateur, String message, LocalDateTime dateHeure) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.message = message;
        this.dateHeure = dateHeure;
    }


	public Notification() {
		// TODO Auto-generated constructor stub
	}

 

  
    
}
