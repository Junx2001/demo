package com.example.demo.groupement;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Groupement {
	@Id
	@SequenceGenerator(
            name="seq_groupement",
            sequenceName="seq_groupement",
            allocationSize=1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="seq_groupement"
    )
	private String idGroupement ;
	private String description;
	private String region ;
	private Double longitude ;
	private Double latitude ;
    private String etat ;
    private String nomImage ;
    private LocalDate dateResolu;
    private String idSousCategorie;
    
	public LocalDate getDateResolu() {
		return dateResolu;
	}
	public void setDateResolu(LocalDate dateResolu) {
		this.dateResolu = dateResolu;
	}
	public String getIdGroupement() {
		return idGroupement;
	}
	public void setIdGroupement(String idGroupement) {
		this.idGroupement = idGroupement;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getNomImage() {
		return nomImage;
	}
	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}
	public Groupement() {}
	public String getIdSousCategorie() {
		return idSousCategorie;
	}
	public void setIdSousCategorie(String idSousCategorie) {
		this.idSousCategorie = idSousCategorie;
	}
}
