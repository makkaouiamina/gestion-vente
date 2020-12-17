package com.gestionVente.service;

import java.util.List;

import com.gestionVente.entities.Commandes;

public interface IServiceCommandes {

    public List<Commandes> findAll();
	
	public void save(Commandes c);
	
	public List<Commandes> findByUser(String login);
	
	public Commandes findById(int id);
	
}
