package com.example.demo.tokenMobile;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class TokenMobile {
	@Id
	private String idToken;
	private String idUserFinal;
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
	public void setDateExpiration(LocalDateTime dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public TokenMobile() {}
	public String getIdUserFinal() {
		return idUserFinal;
	}
	public void setIdUserFinal(String idUserFinal) {
		this.idUserFinal = idUserFinal;
	}
}
