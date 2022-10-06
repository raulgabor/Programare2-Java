package org.musiq.forms;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.musiq.Album;
import org.musiq.Melodie;

@ManagedBean @SessionScoped
public class FormMelodii {
	
	private List<Melodie> melodii = new ArrayList<Melodie>();
	private Melodie melodie;
	
	public List<Melodie> getMelodii() {return melodii;}
	public void setMelodii(List<Melodie> melodii) {this.melodii = melodii;}
	
	public Melodie getMelodie() {return melodie;}
	public void setMelodie(Melodie melodie) {this.melodie = melodie;}
	
	public Integer getIdMelodie(){
		return this.melodie.getId();
	}
	
	public void setIdMelodie(Integer id){
		Integer idx = this.melodii.indexOf(new Melodie(id, null, null, null, null));
		this.melodie = this.melodii.get(idx);
	}
	
	private EntityManager em;
	public FormMelodii() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusiQ_JPA");
		em = emf.createEntityManager();
		init();
	}
	private void init() {
		this.melodii = em.createQuery("SELECT m FROM Melodie m ORDER BY m.id", Melodie.class).getResultList();
		if(!melodii.isEmpty()) {
			this.setMelodie(melodii.get(0));
		}
	}
	
	public void previousMelodie(ActionEvent evt) {
		System.out.println("<<< PREVIOUS MELODIE");
		Integer idxCurent = this.melodii.indexOf(melodie);
		if(idxCurent > 0) {
			this.melodie = this.melodii.get(idxCurent-1);
		}
	}
	
	public void nextMelodie(ActionEvent evt) {
		System.out.println(">>> NEXT MELODIE");
		Integer idxCurent = this.melodii.indexOf(melodie);
		if((idxCurent + 1) < this.melodii.size()) {
			this.melodie = this.melodii.get(idxCurent+1);
		}
	}
	
	public void adaugareMelodie(ActionEvent evt) {
		this.melodie = new Melodie();
		this.melodie.setId(999);
		this.melodie.setNumeMelodie("Melodie noua");
		this.melodie.setAlbum(new Album(null, "Numele album", null, null));
		this.melodie.setGenMelodie("Genul melodiei");
		this.melodie.setDurataMin(1.0);
		this.melodii.add(this.melodie);
	}
	
	public void stergereMelodie(ActionEvent evt) {
		this.melodii.remove(this.melodie);
		if(this.em.contains(this.melodie)) {
			this.em.getTransaction().begin();
			this.em.remove(this.melodie);
			this.em.getTransaction().commit();
		}
		if(!this.melodii.isEmpty()) this.melodie = this.melodii.get(0);
		else this.melodie = null;
	}
	
	public void salvareMelodie(ActionEvent evt) {
		System.out.println("Salvare");
		try {
			this.em.getTransaction().begin();
			this.em.merge(this.melodie);
			this.em.getTransaction().commit();
		}catch(Exception ex) {
			ex.getSuppressed();
		}
	}
	
	public void abandonMelodie(ActionEvent evt) {
		System.out.println("Abandon melodie!");
		em.clear();
		init();
	}

}
