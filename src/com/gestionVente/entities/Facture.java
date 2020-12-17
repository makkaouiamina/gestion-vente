package com.gestionVente.entities;

import java.util.Date;

public class Facture {

	private String nomP;
	private Date dateCmd;
	private int prixP;
	private int qteCmd;
	private int total;
	
	
	public Facture() {
	}

	public Facture(String nomP, Date dateCmd, int prixP, int qteCmd, int total) {
		this.nomP = nomP;
		this.dateCmd = dateCmd;
		this.prixP = prixP;
		this.qteCmd = qteCmd;
		this.total = total;
	}

	public String getNomP() {
		return nomP;
	}

	public void setNomP(String nomP) {
		this.nomP = nomP;
	}

	public Date getDateCmd() {
		return dateCmd;
	}

	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}

	public int getPrixP() {
		return prixP;
	}

	public void setPrixP(int prixP) {
		this.prixP = prixP;
	}

	public int getQteCmd() {
		return qteCmd;
	}

	public void setQteCmd(int qteCmd) {
		this.qteCmd = qteCmd;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
