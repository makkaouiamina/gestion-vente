package com.gestionVente.beans;

import java.io.Serializable;

/*
 * j'ai creer cette classe java ou je vais stocker les attributs depuis la table produitstock et produitPrix
 * 
 * Dans le codePdt je vais stocker codePdt depuis table produitStock
 * nomPdt depuis produitPrix
 * prixPdt depuis ProduitPrix
 * qtePdt depuis qtePdt
 * 
 * 
 *  */

public class ListProduit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codePdt;
	private String nomPdt;
	private int prixPdt;
	private int qtePdt;
	
	
	
	@Override
	public String toString() {
		return "ListProduit [codePdt=" + codePdt + ", nomPdt=" + nomPdt + ", prixPdt=" + prixPdt + ", qtePdt=" + qtePdt
				+ "]";
	}

	public ListProduit() {
		
	}
	
	public ListProduit(int codePdt, String nomPdt, int prixPdt, int qtePtd) {
		super();
		this.codePdt = codePdt;
		this.nomPdt = nomPdt;
		this.prixPdt = prixPdt;
		this.qtePdt = qtePtd;
	}

	

	public int getQtePdt() {
		return qtePdt;
	}

	public void setQtePdt(int qtePdt) {
		this.qtePdt = qtePdt;
	}

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
	public int getPrixPdt() {
		return prixPdt;
	}
	public void setPrixPdt(int prixPdt) {
		this.prixPdt = prixPdt;
	}
	
	
	

}
