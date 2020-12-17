package com.gestionVente.beans;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestionVente.entities.Commandes;
import com.gestionVente.entities.Users;
import com.gestionVente.service.IServiceCommandes;
import com.gestionVente.service.IServiceUsers;

@ManagedBean
@SessionScoped
@Component
public class usersBean {
	
	@Autowired
	IServiceUsers serviceUser;
	
	@Autowired
	IServiceCommandes serviceCommande;
	
	private int codeUser;
	private String login="";
	private String pass="";
	
	
	private List<Users> listUsers;
	
	private String confPassword;
	
	private String newPass;
	
	private Users loginUser = new Users();


	public int getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(int codeUser) {
		this.codeUser = codeUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	public List<Users> getListUsers() {
		return serviceUser.findAll();
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}
	
	

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public Users getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Users loginUser) {
		this.loginUser = loginUser;
	}

	
	public String getNewPass() {
		return newPass;
	}

	
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	//methode de login qui permet a l'utilisateur d'acceder a l'application
	public String loginMethod()throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		
		Users u = serviceUser.getUser(loginUser.getLogin(), loginUser.getPass());
		if( u == null ) {
			u = new Users();
			context.addMessage(null, new FacesMessage("Authentification échouée. Vérifiez le nom d'utilisateur ou le mot de passe."));
			
			return"login";
			
		}
		else if(loginUser.getLogin().equals("admin") && loginUser.getPass().equals("admin")) {
			return "listProduitAdmin";
		}
		else {
			
			return"listProduit";
		}
    	
	}
	
	// methode qui permet a l'utilisateur d'inscrire 
	public String registerMethod() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		listUsers = serviceUser.findAll();
		
		for(Users user : listUsers) {
			if(user.getLogin().equals(loginUser.getLogin())) {
				context.addMessage(null, new FacesMessage("l'utilisateur existe déjà"));
				return "register";
			}
		}
		if(loginUser.getPass().equals(confPassword)) {
			serviceUser.save(loginUser);
			context.getExternalContext().getSessionMap().put("success", "Votre compte est ajoutï¿½ avec succes.");
			
			if(loginUser.getLogin().equals("admin")) {
				return "listProduitAdmin";
			}else {
				return "listProduit";
			}
			
		}else {
			context.addMessage(null, new FacesMessage("Les mots de passe ne correspondent pas") );
			return "register";
		}
		
	}
	
	public String goToEdit() {
		return "editPassword";
	}
	
	
	//methode qui paermet de modifier le mot de passe 
	public String Edit() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		Users u = serviceUser.findByName(loginUser.getLogin());
		System.out.println(u);
		
		if(!u.getPass().equals(loginUser.getPass())) {
			context.addMessage(null, new FacesMessage("Mauvais mot de passe") );
			if(loginUser.getLogin().equals("admin")) {
				return "editPasswordAdmin";
			}else {
				return "editPassword";
			}
			
		}
		else if(newPass.equals(confPassword)) {
				String name= loginUser.getLogin();
				Users user = serviceUser.findByName(name);
				
				user.setPass(newPass);
				
				serviceUser.update(user);
				
				System.out.println(user);
				if(loginUser.getLogin().equals("admin")) {
					return "listProduitAdmin";
				}else {
					return "listProduit";
				}
				
			}
		
		else {
				context.addMessage(null, new FacesMessage("Les mots de passe ne correspondent pas") );
				if(loginUser.getLogin().equals("admin")) {
					return "editPasswordAdmin";
				}else {
					return "editPassword";
				}
			
			
		}
		}
	
	public String addUserPage() {
		return "addUserAdmin";
	}
	
	//methode qui permet a l'admin d'ajouter un utilisateur
	public String addUserAdmin() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		listUsers = serviceUser.findAll();
		
		for(Users user : listUsers) {
			if(user.getLogin().equals(loginUser.getLogin())) {
				context.addMessage(null, new FacesMessage("l'utilisateur existe déjà"));
				return "addUserAdmin";
			}
		}
		if(loginUser.getPass().equals(confPassword)) {
			serviceUser.save(loginUser);
			return "listUsersAdmin";
		}else {
			context.addMessage(null, new FacesMessage("Les mots de passe ne correspondent pas") );
			return "addUserAdmin";
		}
	}
	
	
	// methode qui permet de voir la commande d'un utilisateur 
	public String goToCmd(int id){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Commandes c = serviceCommande.findById(id);

		System.out.println(c);
		
		int total = c.getProduitsPrix().getPrixP() * c.getQteCmd();
		
		context.getExternalContext().getSessionMap().put("codeCmd", c.getCodeCmd());
		context.getExternalContext().getSessionMap().put("dateCmd", c.getDateCmd());
		context.getExternalContext().getSessionMap().put("nomProdCmd", c.getProduitsPrix().getNomP());
		context.getExternalContext().getSessionMap().put("descProdCmd", c.getProduitsPrix().getDescP());
		context.getExternalContext().getSessionMap().put("prixProdCmd", c.getProduitsPrix().getPrixP());
		context.getExternalContext().getSessionMap().put("qteCmd", c.getQteCmd());
		context.getExternalContext().getSessionMap().put("prixTotal", total);
		context.getExternalContext().getSessionMap().put("userCmd", c.getUsers().getLogin());
		
		if(loginUser.getLogin().equals("admin")) {
			return "commandAdmin";
		}
		else {

			return "command";
		}
	}
	
	
}
