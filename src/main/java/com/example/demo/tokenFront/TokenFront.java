package com.example.demo.tokenFront;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class TokenFront {
	@Id
	private String idToken;
	private String idUtilisateur;
	private LocalDateTime dateExpiration;
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	public LocalDateTime getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(LocalDateTime dateValidite) {
		this.dateExpiration = dateValidite;
	}
	public TokenFront() {}
	public String getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
}
