package com.gestionVente.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="produits_prix")
public class ProduitsPrix implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codePdt")
	private int codeP;
	
	@Column(name="nomPdt")
	private String nomP;
	
	@Column(name="descPdt")
	private String descP;
	
	@Column(name="prixPdt")
	private int prixP;
	
	@OneToMany(mappedBy="produitsPrix", fetch=FetchType.EAGER)
	private List<Commandes> commandes = new ArrayList<Commandes>();
			

	public int getCodeP() {
		return codeP;
	}

	public void setCodeP(int codeP) {
		this.codeP = codeP;
	}

	public String getNomP() {
		return nomP;
	}

	public void setNomP(String nomP) {
		this.nomP = nomP;
	}

	public String getDescP() {
		return descP;
	}

	public void setDescP(String descP) {
		this.descP = descP;
	}

	public int getPrixP() {
		return prixP;
	}

	public void setPrixP(int prixP) {
		this.prixP = prixP;
	}

	
	public List<Commandes> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commandes> commandes) {
		this.commandes = commandes;
	}

	

	@Override
	public String toString() {
		return "ProduitsPrix [codeP=" + codeP + ", nomP=" + nomP + ", descP=" + descP + ", prixP=" + prixP + "]";
	}

	public ProduitsPrix() {
		
	}

	public ProduitsPrix(int codeP, String nomP, String descP, int prixP, List<Commandes> commandes) {
		super();
		this.codeP = codeP;
		this.nomP = nomP;
		this.descP = descP;
		this.prixP = prixP;
		this.commandes = commandes;
	}

	public ProduitsPrix(int codeP, String nomP, String descP, int prixP) {
		super();
		this.codeP = codeP;
		this.nomP = nomP;
		this.descP = descP;
		this.prixP = prixP;
	}
	
	
}
