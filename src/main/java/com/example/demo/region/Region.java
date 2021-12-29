package com.example.demo.region;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Region {
	@Id
	@SequenceGenerator(
            name="seqRegion",
            sequenceName="seqRegion",
            allocationSize=1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="seqRegion"
    )
	
	private String idRegion;
	private String nom;
	public String getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(String idRegion) {
		this.idRegion = idRegion;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Region() {}
}
