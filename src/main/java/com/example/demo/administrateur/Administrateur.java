package com.example.demo.administrateur;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Administrateur {
	@Id
	@SequenceGenerator(
            name="seq_administrateur",
            sequenceName="seq_administrateur",
            allocationSize=1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="seq_administrateur"
    )
	
	private String idAdmin;
	private String email;
	private String mdp;
	
	public Administrateur() {}
	public String getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}