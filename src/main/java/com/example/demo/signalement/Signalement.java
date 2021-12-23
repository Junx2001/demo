package com.example.demo.signalement;

import java.time.LocalDate;

public class Signalement {
	private String idSignalement;
	private LocalDate dateSignalement;
	private String description;
	private String idUtilisateur;
	private String region;
	private Double longitude;
	private Double latitude;
	private String idSousCategorie;
	private String nomImage;
	public String getIdSignalement() {
		return idSignalement;
	}
	public void setIdSignalement(String idSignalement) {
		this.idSignalement = idSignalement;
	}
	public LocalDate getDateSignalement() {
		return dateSignalement;
	}
	public void setDateSignalement(LocalDate dateSignalement) {
		this.dateSignalement = dateSignalement;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
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
	public String getIdSousCategorie() {
		return idSousCategorie;
	}
	public void setIdSousCategorie(String idSousCategorie) {
		this.idSousCategorie = idSousCategorie;
	}
	public String getNomImage() {
		return nomImage;
	}
	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}
	public Signalement() {}
}
