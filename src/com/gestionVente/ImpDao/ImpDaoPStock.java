package com.gestionVente.ImpDao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gestionVente.dao.IDAOPStock;
import com.gestionVente.entities.ProduitsPrix;
import com.gestionVente.entities.ProduitsStock;

@Transactional("transactionManagerTwo")
@EnableTransactionManagement
@Repository
public class ImpDaoPStock implements IDAOPStock {

	@Autowired
	@Qualifier("sessionFactoryTwo")
	SessionFactory sessionFactory;
	
	@Override
	public List<ProduitsStock> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from ProduitsStock").list();
	}

	@Override
	public ProduitsStock findById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(ProduitsStock.class, id); 
	}

	@Override
	public ProduitsStock findByName(String produitStock) {
		// TODO Auto-generated method stub
		try {
			return (ProduitsStock)sessionFactory.getCurrentSession().createQuery("from ProduitsStock p where p.nomPdt = :name")
					.setParameter("name", produitStock).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public void update(ProduitsStock produitStock) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(produitStock);
	}

}
