package com.gestionVente.service;

import java.util.List;

import com.gestionVente.entities.ProduitsStock;

public interface IServicePStock {

    public List<ProduitsStock> findAll();
	
	public void save(ProduitsStock p);
	
	public ProduitsStock findById(int id);
	
	public ProduitsStock findByName(String produitStock);
	
	public void update(ProduitsStock produitStock);
	
	public int qteTotal();
	
}
