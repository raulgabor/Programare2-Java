package org.musiq;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.CascadeType.ALL;

@Entity
public class Album implements Comparable<Album>{
	  
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer id;
	private String numeAlbum;
	@ManyToOne  
	private Artist artist;
	private Integer anLansare;
	
	@OneToMany(mappedBy = "album", cascade = ALL)
	private List<Melodie> melodii = new ArrayList<Melodie>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNumeAlbum() {
		return numeAlbum;
	}
	public void setNumeAlbum(String numeAlbum) {
		this.numeAlbum = numeAlbum;
	}
	
	public Integer getAnLansare() {
		return anLansare;
	}
	public void setAnLansare(Integer anLansare) {
		this.anLansare = anLansare;
	}
	
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	public List<Melodie> getMelodii() {
		return melodii;
	}
	public void setMelodii(List<Melodie> melodii) {
		this.melodii = melodii;
	}
	
	public Album(Integer id, String numeAlbum, Artist artist, Integer anLansare) {
		super();
		this.id = id;
		this.numeAlbum = numeAlbum;
		this.artist = artist;
		this.anLansare = anLansare;
	}
	
	public Album(Integer id, String numeAlbum, Artist artist, Integer anLansare, List<Melodie> melodii) {
		super();
		this.id = id;
		this.numeAlbum = numeAlbum;
		this.artist = artist;
		this.anLansare = anLansare;
		this.melodii = melodii;
	}
	
	public Album() {
		super();
	}
	
	public Double getTotalMinAlbum() {
		if (melodii.isEmpty()) return null;
	    
		Double total = 0.0;
		for(Melodie melodie: melodii) {
			total = (Double) total + Math.round(melodie.getDurataMin() * 100.0) / 100.0;
		}
		
		return total;
	}
	
	public Integer getNumarMelodii() {
		if(melodii.isEmpty()) return null;
		
		Integer numarMelodii = 0;
		numarMelodii = numarMelodii + this.melodii.size();
		
		return numarMelodii;
	}
	
	@Override
	public int compareTo(Album o) {
		return this.id.compareTo(o.getId());
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		String mesaj = "Album [id=" + id + ", numeAlbum=" + numeAlbum + ", anLansare="+ anLansare + ", cu melodiile: ";
		
		for(Melodie melodie: melodii)
			mesaj += melodie.getNumeMelodie() + ", " + melodie.getGenMelodie() + ", " + melodie.getDurataMin() + ", ";
		mesaj += "Total Minute Album =" + getTotalMinAlbum()
				+ ", Numar melodii de pe album =" + getNumarMelodii() + "]";
		return mesaj;
	}
	
	
}
