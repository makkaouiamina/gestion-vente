package com.gestionVente.ImpDao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gestionVente.dao.IDAOProduits;
import com.gestionVente.entities.ProduitsPrix;

@Transactional("transactionManagerOne")
@EnableTransactionManagement
@Repository
public class ImpDaoProduits implements IDAOProduits {
	
	@Autowired
	@Qualifier("sessionFactoryOne")
	SessionFactory sessionFactory;

	@Override
	public List<ProduitsPrix> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from ProduitsPrix").list();
	}

	@Override
	public void save(ProduitsPrix p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(p);
		
	}

	@Override
	public void update(ProduitsPrix p) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(p);
	}

	@Override
	public ProduitsPrix findById(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(ProduitsPrix.class, id); 
	}

	@Override
	public ProduitsPrix findByName(String nomPdt) {
		// TODO Auto-generated method stub
		try {
			return (ProduitsPrix) sessionFactory.getCurrentSession().createQuery("from ProduitsPrix p where p.nomP = :name")
					.setParameter("name", nomPdt).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public int maxValue() {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().createQuery("select max(p.prixPdt) from ProduitsPrix p");
		return 0;
	}

	

}
