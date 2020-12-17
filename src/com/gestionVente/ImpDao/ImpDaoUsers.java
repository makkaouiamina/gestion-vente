package com.gestionVente.ImpDao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gestionVente.dao.IDAOUsers;
import com.gestionVente.entities.ProduitsPrix;
import com.gestionVente.entities.Users;

@Transactional("transactionManagerOne")
@EnableTransactionManagement
@Repository
public class ImpDaoUsers implements IDAOUsers {

	@Autowired
	@Qualifier("sessionFactoryOne")
	SessionFactory sessionFactory;
	
	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Users").list();
	}

	@Override
	public void save(Users u) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(u);
	}

	@Override
	public Users findByName(String login) {
		try {
			return (Users) sessionFactory.getCurrentSession().createQuery("from Users u where u.login = :name")
					.setParameter("name", login).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	@Override
	public Users getUser(String login, String pass) {
		
		try {
			return (Users) sessionFactory.getCurrentSession().createQuery( "from Users u where u.login = :name and u.pass = :pass ")
					.setParameter("name", login)
					.setParameter("pass", pass) 
					.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	@Override
	public void update(Users u) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(u);
	}

	@Override
	public Users findById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Users.class, id);
				
	}

	

}
