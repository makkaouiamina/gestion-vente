package com.gestionVente.dao;

import java.util.List;

import com.gestionVente.entities.Commandes;

public interface IDAOCommandes {

	public List<Commandes> findAll();
	
	public void save(Commandes c);
	
	public void update(Commandes c);
	
	public List<Commandes> findByUser(String login);
	
	public Commandes findById(int id);
	
}

