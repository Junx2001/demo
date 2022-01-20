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
	@SequenceGenerator(
            name="seq_token_front",
            sequenceName="seq_token_front",
            allocationSize=1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="seq_token_front"
    )
	private String idToken;
	private String idUtilisateur;
	private LocalDateTime dateValidite;
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	public LocalDateTime getDateValidite() {
		return dateValidite;
	}
	public void setDateValidite(LocalDateTime dateValidite) {
		this.dateValidite = dateValidite;
	}
	public TokenFront() {}
	public String getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
}
