package com.gestionVente.beans;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestionVente.entities.Commandes;
import com.gestionVente.entities.Facture;
import com.gestionVente.entities.ProduitsPrix;
import com.gestionVente.entities.ProduitsStock;
import com.gestionVente.entities.Users;
import com.gestionVente.service.IServiceCommandes;
import com.gestionVente.service.IServicePStock;
import com.gestionVente.service.IServiceProduits;
import com.gestionVente.service.IServiceUsers;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@SessionScoped
@Component
public class CommandesBean {
	
	@Autowired
	IServiceCommandes serviceCommande;
	
	@Autowired
	IServiceUsers serviceUser;
	
	@Autowired
	IServiceProduits serviceProduit;
	
	@Autowired 
	IServicePStock serviceStock;
	
	private List<Commandes> commandList;
	
	private List<ProduitsPrix> produitList;
	
	private List<Users> usersList;
	
	private String qteCmd;
	
	private Commandes commande = new Commandes();
	
	
	private List<Commandes> allCmd;
	
	private String login;
	
	private String nomPdt;
	

	public List<ProduitsPrix> getProduitList() {
		return serviceProduit.findAll();
	}

	public void setProduitList(List<ProduitsPrix> produitList) {
		this.produitList = produitList;
	}

	public List<Users> getUsersList() {
		return serviceUser.findAll();
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public Commandes getCommande() {
		
		return commande;
	}

	public void setCommande(Commandes commande) {
		this.commande = commande;
	}
	
	// get la liste des commandes d'un seule utilisateur
	
	public List<Commandes> getCommandList(String login) {
		
		commandList = serviceCommande.findByUser(login);
		
		System.out.println(commandList);
		return commandList;
	}

	public void setCommandList(List<Commandes> commandList) {
		this.commandList = commandList;
	}

	public String getQteCmd() {
		return qteCmd;
	}

	public void setQteCmd(String qteCmd) {
		this.qteCmd = qteCmd;
	}

	public List<Commandes> getAllCmd() {
		return serviceCommande.findAll();
	}

	public void setAllCmd(List<Commandes> allCmd) {
		this.allCmd = allCmd;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomPdt() {
		return nomPdt;
	}

	public void setNomPdt(String nomPdt) {
		this.nomPdt = nomPdt;
	}

	/*enregistrer une commande.. cette methode prend comme argument le nom de l'utilisateur connecter(String log)
	 *  et le nom du produit selectioné depuis la liste des produits
	 *  j'ai fais deux requetes : une pour trouver le produit avec son non l'autre pour trouver l'utilisateur dans labase de donnees en avec son nom
	 *  en utilisant la methode appelé depuis les sevice findByName()
	 *  j'ai fait la soustraction du quantite saisie par l'utilisateur dans le stock
	
	*/
	
	public String saveCommand(String nom, String log) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		ProduitsPrix produit = serviceProduit.findByName(nom);
		Users user = serviceUser.findByName(log);
		

		ProduitsStock produitStock = new ProduitsStock();
		
		produitStock = serviceStock.findByName(produit.getNomP());
		
		LocalDate dateNow = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date d = Date.from(dateNow.atStartOfDay(defaultZoneId).toInstant());
		
		if(Integer.valueOf(qteCmd) == 0) {
			context.addMessage(null, new FacesMessage("Quantite ne peut pas etre null"));
			qteCmd="";
			return "addCommand";
		}
		else if(Integer.valueOf(qteCmd) <= produitStock.getQtePdt()) {
			
			Commandes commande = new Commandes();
			
			commande.setProduitsPrix(produit);
			commande.setUsers(user);
			
			commande.setDateCmd(d);
	
		    commande.setQteCmd(Integer.valueOf(qteCmd));
			
			
			
			serviceCommande.save(commande);
			
			
			int quantite = produitStock.getQtePdt() - commande.getQteCmd();
			
			produitStock.setQtePdt(quantite);
			
			serviceStock.update(produitStock);
			
			int total = commande.getProduitsPrix().getPrixP() * commande.getQteCmd();
			
			context.getExternalContext().getSessionMap().put("codeCmd", commande.getCodeCmd());
			context.getExternalContext().getSessionMap().put("dateCmd", commande.getDateCmd());
			context.getExternalContext().getSessionMap().put("nomProdCmd", commande.getProduitsPrix().getNomP());
			context.getExternalContext().getSessionMap().put("descProdCmd", commande.getProduitsPrix().getDescP());
			context.getExternalContext().getSessionMap().put("prixProdCmd", commande.getProduitsPrix().getPrixP());
			context.getExternalContext().getSessionMap().put("qteCmd", commande.getQteCmd());
			context.getExternalContext().getSessionMap().put("prixTotal", total);
			context.getExternalContext().getSessionMap().put("userCmd", commande.getUsers().getLogin());
			System.out.println(commande);
			return "command";
		}else {
			context.addMessage(null, new FacesMessage("quantité supérieure à celle trouvée en stock "));
			qteCmd="";
			return "addCommand";
		}
		
		
	}
	
	public String lCommand() {

		return "listCommand";
	}
	
	// method qui permet de generer le pdf du commande avec jasper Reports
	public void printPDF(int code) throws JRException, IOException {
		
		Commandes commande = serviceCommande.findById(code);
		
		
		List<Facture> datasource = new ArrayList<Facture>();
		
		Date d = commande.getDateCmd();
		
		int total = commande.getQteCmd() * commande.getProduitsPrix().getPrixP();
		
		datasource.add(new Facture(commande.getProduitsPrix().getNomP(), commande.getDateCmd(), commande.getProduitsPrix().getPrixP(), commande.getQteCmd(), total));
		
		
		String filename = "Commande.pdf";
		
		String jasperPath = "/resources/facture.jasper";
		
		this.PDf(null, jasperPath, datasource, filename);
	}
	
	public void PDf(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
		
		String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
		
		File file = new File(relativeWebPath);
		
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
		
		JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		response.addHeader("Content-disposition", "attachment;filename=" + fileName);
		
		ServletOutputStream stream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(print, stream);
		
		FacesContext.getCurrentInstance().responseComplete();
	}

	
	public String addCmdPage() {
		return "addCommandAdmin";
	}
	
	// methode qui permit a l'admin d'aujouter une commande pour un utilisateur et un produit saisie dans l'input
	public String addCommandAdmin() {
        
		FacesContext context = FacesContext.getCurrentInstance();
		
		ProduitsPrix produit = serviceProduit.findByName(nomPdt);
		Users user = serviceUser.findByName(login);
		
        ProduitsStock produitStock = new ProduitsStock();
		
		produitStock = serviceStock.findByName(produit.getNomP());
		
		LocalDate dateNow = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date d = Date.from(dateNow.atStartOfDay(defaultZoneId).toInstant());
		
		if(Integer.valueOf(qteCmd) == 0) {
			context.addMessage(null, new FacesMessage("Quantite ne peut pas etre null"));
			qteCmd="";
			return "addCommandAdmin";
		}
		else if(Integer.valueOf(qteCmd) <= produitStock.getQtePdt()) {
			
			Commandes commande = new Commandes();
			
			commande.setProduitsPrix(produit);
			commande.setUsers(user);
			
			commande.setDateCmd(d);
	
		    commande.setQteCmd(Integer.valueOf(qteCmd));
			
			
			
			serviceCommande.save(commande);
			
			
			int quantite = produitStock.getQtePdt() - commande.getQteCmd();
			
			produitStock.setQtePdt(quantite);
			
			serviceStock.update(produitStock);
			
			
			System.out.println(commande);
			
            int total = commande.getProduitsPrix().getPrixP() * commande.getQteCmd();
			
			context.getExternalContext().getSessionMap().put("codeCmd", commande.getCodeCmd());
			context.getExternalContext().getSessionMap().put("dateCmd", commande.getDateCmd());
			context.getExternalContext().getSessionMap().put("nomProdCmd", commande.getProduitsPrix().getNomP());
			context.getExternalContext().getSessionMap().put("descProdCmd", commande.getProduitsPrix().getDescP());
			context.getExternalContext().getSessionMap().put("prixProdCmd", commande.getProduitsPrix().getPrixP());
			context.getExternalContext().getSessionMap().put("qteCmd", commande.getQteCmd());
			context.getExternalContext().getSessionMap().put("prixTotal", total);
			context.getExternalContext().getSessionMap().put("userCmd", commande.getUsers().getLogin());
			System.out.println(commande);
			return "commandAdmin";
		}else {
			context.addMessage(null, new FacesMessage("quantité supérieure à celle trouvée en stock"));
			qteCmd="";
			return "addCommandAdmin";
		}
	}
	
	//methode qui permet de lister les commandes d'un seul utilisateur
	public String listCommandUser(String login) {
		FacesContext context = FacesContext.getCurrentInstance();
		
       Users user = serviceUser.findByName(login);
		
        context.getExternalContext().getSessionMap().put("login", user.getLogin());
        
		return "listCommandUser";
	}

}
