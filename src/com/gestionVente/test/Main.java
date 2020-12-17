package com.gestionVente.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gestionVente.dao.IDAOCommandes;
import com.gestionVente.dao.IDAOPStock;
import com.gestionVente.dao.IDAOProduits;
import com.gestionVente.dao.IDAOUsers;
import com.gestionVente.entities.Commandes;
import com.gestionVente.entities.ProduitsPrix;
import com.gestionVente.entities.Users;
import com.gestionVente.service.IServiceCommandes;
import com.gestionVente.service.IServicePStock;
import com.gestionVente.service.IServiceProduits;
import com.gestionVente.service.IServiceUsers;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		
		IDAOProduits daoP = (IDAOProduits)ctx.getBean("impDaoProduits");
		
		IDAOPStock daoPdtStock = (IDAOPStock)ctx.getBean("impDaoPStock");
		
		IDAOCommandes daoCom =(IDAOCommandes)ctx.getBean("impDaoCommandes");
		
	    IDAOUsers daouser =(IDAOUsers) ctx.getBean("impDaoUsers");
	    
	    IServiceProduits serviceP = (IServiceProduits) ctx.getBean("impServiceProduits") ;
	    
	    IServicePStock serviceS = (IServicePStock) ctx.getBean("impServicePStock");
	    
	    IServiceCommandes serviceC =(IServiceCommandes) ctx.getBean("impServiceCommandes");
	    
	    IServiceUsers serviceU = (IServiceUsers) ctx.getBean("impServiceUsers");
		
	    /*
	     System.out.println(daoP.findAll());
		System.out.println("---------------------------------");
		
		System.out.println(daoPdtStock.findAll());

		System.out.println("---------------------------------");
		
		System.out.println(daoCom.findAll());
		
		System.out.println("---------------------------------");
		
		System.out.println(daouser.findAll());
	     * */
	    
	    System.out.println(serviceP.findAll());
	    System.out.println(serviceS.findAll());
	    System.out.println(serviceC.findAll());
	    System.out.println(serviceU.findAll());
	}

}
