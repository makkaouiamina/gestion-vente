package com.gestionVente.ImpService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionVente.dao.IDAOUsers;
import com.gestionVente.entities.Users;
import com.gestionVente.service.IServiceUsers;

@Service
public class ImpServiceUsers implements IServiceUsers {

	@Autowired
	IDAOUsers daoU;
	
	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return daoU.findAll();
	}

	@Override
	public void save(Users u) {
		// TODO Auto-generated method stub
		daoU.save(u);
	}

	@Override
	public Users getUser(String login, String pass) {
		// TODO Auto-generated method stub
		return daoU.getUser(login, pass);
	}

	@Override
	public Users findByName(String login) {
		// TODO Auto-generated method stub
		return daoU.findByName(login);
	}

	@Override
	public void update(Users u) {
		// TODO Auto-generated method stub
		daoU.update(u);
	}

}
