package com.gestionVente.dao;

import java.util.List;


import com.gestionVente.entities.Users;

public interface IDAOUsers {

	public List<Users> findAll();
	
	public void save(Users u);
	
	public Users findByName(String login);
	
	public Users getUser(String login, String pass);
	
	public void update(Users u);
	
	public Users findById(int id);
	
	
	}
