package com.example.demo.detailGroupement;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class DetailGroupement {
	private String idGroupement;
	private String idSignalement;
	public String getIdGroupement() {
		return idGroupement;
	}
	public void setIdGroupement(String idGroupement) {
		this.idGroupement = idGroupement;
	}
	public String getIdSignalement() {
		return idSignalement;
	}
	public void setIdSignalement(String idSignalement) {
		this.idSignalement = idSignalement;
	}
	public DetailGroupement() {}
}
