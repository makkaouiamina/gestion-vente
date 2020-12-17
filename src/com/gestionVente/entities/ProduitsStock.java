package com.gestionVente.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="produits_stock")
public class ProduitsStock implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int codePdt;
	
	@Column
	private String nomPdt;
	
	@Column
	private int qtePdt;
	
	@Column
	private String descPdt;
	
	@Column
	private int prixPdt;

	public int getCodePdt() {
		return codePdt;
	}

	public void setCodePdt(int codePdt) {
		this.codePdt = codePdt;
	}

	public String getNomPdt() {
		return nomPdt;
	}

	public void setNomPdt(String nomPdt) {
		this.nomPdt = nomPdt;
	}

	public int getQtePdt() {
		return qtePdt;
	}

	public void setQtePdt(int qtePdt) {
		this.qtePdt = qtePdt;
	}

	public String getDescPdt() {
		return descPdt;
	}

	public void setDescPdt(String descPdt) {
		this.descPdt = descPdt;
	}

	public int getPrixPdt() {
		return prixPdt;
	}

	public void setPrixPdt(int prixPdt) {
		this.prixPdt = prixPdt;
	}

	public ProduitsStock(int codePdt, String nomPdt, int qtePdt, String descPdt, int prixPdt) {
		super();
		this.codePdt = codePdt;
		this.nomPdt = nomPdt;
		this.qtePdt = qtePdt;
		this.descPdt = descPdt;
		this.prixPdt = prixPdt;
	}
	
	public ProduitsStock() {
		super();
	}

	@Override
	public String toString() {
		return "ProduitsStock [codePdt=" + codePdt + ", nomPdt=" + nomPdt + ", qtePdt=" + qtePdt + ", descPdt="
				+ descPdt + ", prixPdt=" + prixPdt + "]";
	}
	
}
