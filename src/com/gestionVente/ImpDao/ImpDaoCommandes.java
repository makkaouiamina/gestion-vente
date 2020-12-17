package com.gestionVente.ImpDao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gestionVente.dao.IDAOCommandes;
import com.gestionVente.entities.Commandes;

@Transactional("transactionManagerOne")
@EnableTransactionManagement
@Repository
public class ImpDaoCommandes implements IDAOCommandes {

	@Autowired
	@Qualifier("sessionFactoryOne")
	SessionFactory sessionFactory;
	
	@Override
	public List<Commandes> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Commandes").list();
	}

	@Override
	public void save(Commandes c) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(c);

	}

	@Override
	public List<Commandes> findByUser(String login) {
		try {
			return sessionFactory.getCurrentSession().createQuery("from Commandes c where c.users.login = :name")
					.setParameter("name", login)
					.list();
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Commandes findById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Commandes.class, id);
	}

	@Override
	public void update(Commandes c) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(c);
	}

}
