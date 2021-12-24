package com.example.demo.token;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Token {
	@Id
	@SequenceGenerator(
            name="seq_token",
            sequenceName="seq_token",
            allocationSize=1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="seq_token"
    )
	private String idToken;
	private String idAdmin;
	private LocalDateTime dateValidite;
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	public String getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}
	public LocalDateTime getDateValidite() {
		return dateValidite;
	}
	public void setDateValidite(LocalDateTime dateValidite) {
		this.dateValidite = dateValidite;
	}
	public Token() {}
}
