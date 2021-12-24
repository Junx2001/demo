package com.example.demo.sousCategorie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class SousCategorie {
	@Id
	@SequenceGenerator(
            name="seq_sousCategorie",
            sequenceName="seq_sousCategorie",
            allocationSize=1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="seq_sousCategorie"
    )
	private String idSousCategorie;
	private String label;
	private String idCategorie;
	public String getIdSousCategorie() {
		return idSousCategorie;
	}
	public void setIdSousCategorie(String idSousCategorie) {
		this.idSousCategorie = idSousCategorie;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(String idCategorie) {
		this.idCategorie = idCategorie;
	}
}
