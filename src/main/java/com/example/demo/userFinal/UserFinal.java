package com.example.demo.userFinal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class UserFinal {
	@Id
	@SequenceGenerator(
            name="seq_user_final",
            sequenceName="seq_user_final",
            allocationSize=1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="seq_user_final"
    )
	private String idUserFinal;
	private String email;
	private String mdp;
	
	public String getIdUserFinal() {
		return idUserFinal;
	}
	public void setIdUserFinal(String idUserFinal) {
		this.idUserFinal = idUserFinal;
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
	public UserFinal() {}	
}
