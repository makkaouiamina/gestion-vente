package com.gestionVente.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestionVente.entities.ProduitsPrix;
import com.gestionVente.entities.ProduitsStock;
import com.gestionVente.service.IServicePStock;
import com.gestionVente.service.IServiceProduits;

@ManagedBean(name = "produitsBean")
@SessionScoped
@Component
public class ProduitsBean {
	
	@Autowired
	IServiceProduits serviceP;
	
	@Autowired
	IServicePStock serviceS;
	
	private int qteTotal;
	private int prixTotal;
	
	private String codePdt;
	private String nomPdt;
	private String descPdt;
	private String prixPdt;
	
	private List<ProduitsPrix> produitsList;
	
	private List<ProduitsStock> pStockList;
 
	private List<ListProduit> listProduits;
	
	ProduitsStock produitStock = new ProduitsStock() ;

	// methode get la liste des produits 
	// si la quantite du produit dans le stock = 0 le produit ne sera pas affiche dans la liste
	//
	public List<ListProduit> getListProduits() {
		
		listProduits = new ArrayList<ListProduit>();
		
		pStockList = serviceS.findAll();
		produitsList = serviceP.findAll();
		
		
		for(ProduitsStock ps : pStockList) {
			for(ProduitsPrix px : produitsList) {
				if(ps.getNomPdt().equals(px.getNomP())) {
					ListProduit produit = new ListProduit();
					

					if(ps.getQtePdt() == 0) {
						   
							if(produit.getCodePdt() == ps.getCodePdt()) {
								listProduits.remove(produit);
								
								ps.setQtePdt(0);
								serviceS.update(ps);
								
								return listProduits;
							}}else {

								produit.setCodePdt(ps.getCodePdt());
								produit.setNomPdt(px.getNomP());
								produit.setPrixPdt(px.getPrixP());
								produit.setQtePdt(ps.getQtePdt());
								
								listProduits.add(produit);
							}
					
					
				}
			}
		}
		return listProduits;
	}

	public String getCodePdt() {
		return codePdt;
	}

	public void setCodePdt(String codePdt) {
		this.codePdt = codePdt;
	}

	public String getNomPdt() {
		return nomPdt;
	}

	public void setNomPdt(String nomPdt) {
		this.nomPdt = nomPdt;
	}

	public String getDescPdt() {
		return descPdt;
	}

	public void setDescPdt(String descPdt) {
		this.descPdt = descPdt;
	}

	public String getPrixPdt() {
		return prixPdt;
	}

	public void setPrixPdt(String prixPdt) {
		this.prixPdt = prixPdt;
	}


	public List<ProduitsStock> getpStockList() {
		return serviceS.findAll();
	}

	public void setpStockList(List<ProduitsStock> pStockList) {
		this.pStockList = pStockList;
	}

	public List<ProduitsPrix> getProuitsList() {
		return produitsList;
	}

	public void setProuitsList(List<ProduitsPrix> produitsList) {
		this.produitsList = produitsList;
	}
	
	public ProduitsStock getProduitStock() {
		return produitStock;
	}

	public void setProduitStock(ProduitsStock produitStock) {
		this.produitStock = produitStock;
	}

	public int getQteTotal() {
		return serviceS.qteTotal();
	}

	public void setQteTotal(int qteTotal) {
		this.qteTotal = qteTotal;
	}

	public int getPrixTotal() {
		return serviceP.prixTotal();
	}

	public void setPrixTotal(int prixTotal) {
		this.prixTotal = prixTotal;
	}

	// methode ajout d'une commande a un produit selectioné du tableau
	public String addCommande(String nomPdt) {

		FacesContext context = FacesContext.getCurrentInstance();
		
			ProduitsPrix produit = serviceP.findByName(nomPdt);
			context.getExternalContext().getSessionMap().put("nomPdt", produit.getNomP());
			System.out.println(produit.getNomP());
			return "addCommand";
		
	}
	
	public String produitPage() {
		
		return "addProduitAdmin";
	}
	
	// methode qui permet a l'admin d'ajouter un produit 
	
	public String addProduit() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		for(ProduitsPrix p : serviceP.findAll()) {
			if(p.getNomP().equals(nomPdt)) {
				context.addMessage(null, new FacesMessage("Produit existe deja") );
				return "addProduitAdmin";
			}
		}
		
		ProduitsPrix pdt = new ProduitsPrix();
		
		pdt.setNomP(nomPdt);
		pdt.setDescP(descPdt);
		pdt.setPrixP(Integer.parseInt(prixPdt));
		
		serviceP.save(pdt);
		
		nomPdt="";
		descPdt="";
		
		
		return "listProduitAdmin";
	}
	


}