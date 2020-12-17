package com.gestionVente.ImpService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionVente.dao.IDAOProduits;
import com.gestionVente.entities.ProduitsPrix;
import com.gestionVente.service.IServiceProduits;

@Service
public class ImpServiceProduits implements IServiceProduits {

	@Autowired
	IDAOProduits daoP;
	
	@Override
	public List<ProduitsPrix> findAll() {
		// TODO Auto-generated method stub
		return daoP.findAll();
	}

	@Override
	public void save(ProduitsPrix p) {
		// TODO Auto-generated method stub
		daoP.save(p);
	}

	@Override
	public void update(ProduitsPrix p) {
		// TODO Auto-generated method stub
		daoP.update(p);
		
	}

	@Override
	public ProduitsPrix findById(int id) {
		// TODO Auto-generated method stub
		return daoP.findById(id);
	}

	@Override
	public ProduitsPrix findByName(String nomPdt) {
		// TODO Auto-generated method stub
		return daoP.findByName(nomPdt);
	}

	@Override
	public int prixTotal() {
		// TODO Auto-generated method stub
		
		int prixTotal=0;
		
		List<ProduitsPrix> produits = daoP.findAll();
		
		for(ProduitsPrix p : produits ) {
			prixTotal += p.getPrixP();
		}
		
		return prixTotal;
	}

	@Override
	public int maxValue() {
		// TODO Auto-generated method stub
		return daoP.maxValue();
	}
}
