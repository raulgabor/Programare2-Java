package org.musiq.forms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.musiq.Album;
import org.musiq.Artist;

@ManagedBean @SessionScoped
public class FormAlbume {
	
	private List<Album> albume = new ArrayList<Album>();
	private Album album;
	
	public List<Album> getAlbume() { return albume; }
	public void setAlbume(List<Album> albume) { this.albume = albume; }
	
	public Album getAlbum() {return album;}
	public void setAlbum(Album album) { this.album = album; }
	
	public Integer getIdAlbum() {
		return this.album.getId();
	}
	
	public void setIdAlbum(Integer id) {
		Integer idx = this.albume.indexOf(new Album(id, null, null, null));
		this.album = this.albume.get(idx);
	}
	
	private EntityManager em;
	public FormAlbume() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusiQ_JPA");
		em = emf.createEntityManager();
		initModelAlbume();
	}
	
	private void initModelAlbume() {
		this.albume = em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
		if(this.albume != null && !this.albume.isEmpty()) {
			Collections.sort(this.albume, (a1, a2) -> a1.getId().compareTo(a2.getId()));
			if(!this.albume.contains(this.album)) this.album = this.albume.get(0);
		}
		if(this.album == null) adaugareAlbum(null);
	}
	
	public void previousAlbum(ActionEvent evt) {
		System.out.println("<<< PREVIOUS ALBUM");
		Integer idxCurent = this.albume.indexOf(album);
		if(idxCurent > 0) {
			this.album = this.albume.get(idxCurent-1);
		}
	}
	
	public void nextAlbum(ActionEvent evt) {
		System.out.println(">>> NEXT ALBUM");
		Integer idxCurent = this.albume.indexOf(album);
		if((idxCurent + 1) < this.albume.size()) {
			this.album = this.albume.get(idxCurent+1);
		}
	}
	
	public void adaugareAlbum(ActionEvent evt) {
		this.album = new Album();
		this.album.setId(999);
		this.album.setNumeAlbum("Album nou");
		this.album.setArtist(new Artist(null, null, null, null, "Nume Scena nou", null));
		this.album.setAnLansare(1900);
		this.albume.add(this.album);
	}
	
	public void stergereAlbum(ActionEvent evt) {
		this.albume.remove(this.album);
		if(this.em.contains(this.album)) {
			this.em.getTransaction().begin();
			this.em.remove(this.album);
			this.em.getTransaction().commit();
		}
		if(!this.albume.isEmpty()) this.album = this.albume.get(0);
		else this.album = null;
	}
	
	public void salvareAlbum(ActionEvent evt) {
		System.out.println("Salvare");
		try {
			this.em.getTransaction().begin();
			this.em.merge(this.album);
			this.em.getTransaction().commit();
		}catch(Exception ex) {
			ex.getSuppressed();
		}
	}
	
	public void abandonAlbum(ActionEvent evt) {
		System.out.println("Abandon album!");
		em.clear();
		initModelAlbume();
	}

}
