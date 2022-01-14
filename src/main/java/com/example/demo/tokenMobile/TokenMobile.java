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
	@SequenceGenerator(
            name="seq_token_mobile",
            sequenceName="seq_token_mobile",
            allocationSize=1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="seq_token_mobile"
    )
	private String idToken;
	private String idUserFinal;
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
	public TokenMobile() {}
	public String getIdUserFinal() {
		return idUserFinal;
	}
	public void setIdUserFinal(String idUserFinal) {
		this.idUserFinal = idUserFinal;
	}
}
