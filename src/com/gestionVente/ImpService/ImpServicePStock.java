package com.gestionVente.ImpService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionVente.dao.IDAOPStock;
import com.gestionVente.entities.ProduitsStock;
import com.gestionVente.service.IServicePStock;

@Service
public class ImpServicePStock implements IServicePStock {

	@Autowired
	IDAOPStock daoPStock;
	
	@Override
	public List<ProduitsStock> findAll() {
		// TODO Auto-generated method stub
		return daoPStock.findAll();
	}

	@Override
	public void save(ProduitsStock p) {
		// TODO Auto-generated method stub
	}

	@Override
	public ProduitsStock findById(int id) {
		// TODO Auto-generated method stub
		return daoPStock.findById(id);
	}

	@Override
	public ProduitsStock findByName(String produitStock) {
		// TODO Auto-generated method stub
		return daoPStock.findByName(produitStock);
	}

	@Override
	public void update(ProduitsStock produitStock) {
		// TODO Auto-generated method stub
		daoPStock.update(produitStock);
	}

	@Override
	public int qteTotal() {
		// TODO Auto-generated method stub
		int qteTotal =0;
		
		List<ProduitsStock> produitsStock = daoPStock.findAll();
		
		for(ProduitsStock ps : produitsStock ) {

			qteTotal += ps.getQtePdt();
		}
		
		return qteTotal;
	}

	

}
