package org.musiq.forms;

import java.util.ArrayList;
//import java.util.Date;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.musiq.Persoana;

@ManagedBean @SessionScoped
public class FormPersoane {
	
	private List<Persoana> persoane = new ArrayList<Persoana>();
	private Persoana persoana;
	
	public List<Persoana> getPersoane() {return persoane;}
	public void setPersoane(List<Persoana> persoane) {this.persoane = persoane;}
	
	public Persoana getPersoana() {return persoana;}
	public void setPersoana(Persoana persoana) {this.persoana = persoana;}
	
	public Integer getIdPersoana(){
		return this.persoana.getId();
	}
	
	public void setIdPersoana(Integer id){
		Integer idx = this.persoane.indexOf(new Persoana(id, null, null, null));
		this.persoana = this.persoane.get(idx);
	}

	
	private EntityManager em;
	public FormPersoane() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusiQ_JPA");
		em = emf.createEntityManager();
		init();
	}
	private void init() {
		this.persoane = em.createQuery("SELECT p FROM Persoana p ORDER BY p.id", Persoana.class).getResultList();
		if(!persoane.isEmpty()) {
			this.setPersoana(persoane.get(0));
		}
	}
	
	public void previousPersoana(ActionEvent evt) {
		System.out.println("<<< PREVIOUS PERSOANA");
		Integer idxCurent = this.persoane.indexOf(persoana);
		if(idxCurent > 0) {
			this.persoana = this.persoane.get(idxCurent-1);
		}
	}
	
	public void nextPersoana(ActionEvent evt) {
		System.out.println(">>> NEXT PERSOANA");
		Integer idxCurent = this.persoane.indexOf(persoana);
		if((idxCurent + 1) < this.persoane.size()) {
			this.persoana = this.persoane.get(idxCurent+1);
		}
	}
	
	public void adaugarePersoana(ActionEvent evt) {
		this.persoana = new Persoana();
		this.persoana.setId(999);
		this.persoana.setNumePersoana("Persoana noua");
		//this.persoana.setDataNasterii(new Date(99-01-01));
		this.persoana.setNationalitate("Nationalitate");
		this.persoane.add(this.persoana);
	}
	
	public void stergerePersoana(ActionEvent evt) {
		this.persoane.remove(this.persoana);
		if(this.em.contains(this.persoana)) {
			this.em.getTransaction().begin();
			this.em.remove(this.persoana);
			this.em.getTransaction().commit();
		}
		if(!this.persoane.isEmpty()) this.persoana = this.persoane.get(0);
		else this.persoana = null;
	}
	
	public void salvarePersoana(ActionEvent evt) {
		System.out.println("Salvare");
		try {
			this.em.getTransaction().begin();
			this.em.merge(this.persoana);
			this.em.getTransaction().commit();
		}catch(Exception ex) {
			ex.getSuppressed();
		}
	}
	
	public void abandonPersoana(ActionEvent evt) {
		System.out.println("Abandon persoana!");
		em.clear();
		init();
	}
}
