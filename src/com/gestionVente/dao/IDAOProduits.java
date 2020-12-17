package com.gestionVente.dao;

import java.util.List;

import com.gestionVente.entities.ProduitsPrix;

public interface IDAOProduits {

	public List<ProduitsPrix> findAll();
	
	public void save(ProduitsPrix p);
	
	public void update(ProduitsPrix p);
	
	public ProduitsPrix findById(int id);
	
	public ProduitsPrix findByName(String nomPdt);
	
	public int maxValue();
	
	
}
