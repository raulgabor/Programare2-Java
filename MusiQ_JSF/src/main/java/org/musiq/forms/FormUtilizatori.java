package org.musiq.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.musiq.Utilizator;

@ManagedBean @SessionScoped
public class FormUtilizatori {
	
	private List<Utilizator> utilizatori = new ArrayList<Utilizator>();
	private Utilizator utilizator;
	
	public List<Utilizator> getUtilizatori() {return utilizatori;}
	public void setUtilizatori(List<Utilizator> utilizatori) {this.utilizatori = utilizatori;}
	
	public Utilizator getUtilizator() {return utilizator;}
	public void setUtilizator(Utilizator utilizator) {this.utilizator = utilizator;}
	
	public Integer getIdUtilizator(){
		return this.utilizator.getId();
	}
	
	public void setIdUtilizator(Integer id){
		Integer idx = this.utilizatori.indexOf(new Utilizator(id, null, null, null, null));
		this.utilizator = this.utilizatori.get(idx);
	}
	
	private EntityManager em;
	public FormUtilizatori() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusiQ_JPA");
		em = emf.createEntityManager();
		init();
	}
	private void init() {
		this.utilizatori = em.createQuery("SELECT u FROM Utilizator u ORDER BY u.id", Utilizator.class).getResultList();
		if(!utilizatori.isEmpty()) {
			this.setUtilizator(utilizatori.get(0));
		}
	}
	
	public void previousUtilizator(ActionEvent evt) {
		System.out.println("<<< PREVIOUS UTILIZATOR");
		Integer idxCurent = this.utilizatori.indexOf(utilizator);
		if(idxCurent > 0) {
			this.utilizator = this.utilizatori.get(idxCurent-1);
		}
	}
	
	public void nextUtilizator(ActionEvent evt) {
		System.out.println(">>> NEXT UTILIZATOR");
		Integer idxCurent = this.utilizatori.indexOf(utilizator);
		if((idxCurent + 1) < this.utilizatori.size()) {
			this.utilizator = this.utilizatori.get(idxCurent+1);
		}
	}
	
	public void adaugareUtilizator(ActionEvent evt) {
		this.utilizator = new Utilizator();
		this.utilizator.setId(999);
		this.utilizator.setNumePersoana("Persoana noua");
		this.utilizator.setDataNasterii(new Date(99-01-01));
		this.utilizator.setNationalitate("Nationalitate");
		this.utilizator.setNickname("Nickname nou");
		this.utilizatori.add(this.utilizator);
	}
	
	public void stergereUtilizator(ActionEvent evt) {
		this.utilizatori.remove(this.utilizator);
		if(this.em.contains(this.utilizator)) {
			this.em.getTransaction().begin();
			this.em.remove(this.utilizator);
			this.em.getTransaction().commit();
		}
		if(!this.utilizatori.isEmpty()) this.utilizator = this.utilizatori.get(0);
		else this.utilizator = null;
	}
	
	public void salvareUtilizator(ActionEvent evt) {
		System.out.println("Salvare");
		try {
			this.em.getTransaction().begin();
			this.em.merge(this.utilizator);
			this.em.getTransaction().commit();
		}catch(Exception ex) {
			ex.getSuppressed();
		}
	}
	
	public void abandonUtilizator(ActionEvent evt) {
		System.out.println("Abandon utilizator!");
		em.clear();
		init();
	}

}
