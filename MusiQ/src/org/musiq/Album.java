package org.musiq;

import java.util.ArrayList;
import java.util.List;

public class Album implements Comparable<Album>{
	
	private Integer id;
	private String numeAlbum;
	private Integer anLansare;
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
	
	public List<Melodie> getMelodii() {
		return melodii;
	}
	public void setMelodii(List<Melodie> melodii) {
		this.melodii = melodii;
	}
	
	public Album(Integer id, String numeAlbum, Integer anLansare) {
		super();
		this.id = id;
		this.numeAlbum = numeAlbum;
		this.anLansare = anLansare;
	}
	
	public Album(Integer id, String numeAlbum, Integer anLansare, List<Melodie> melodii) {
		super();
		this.id = id;
		this.numeAlbum = numeAlbum;
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
			total = total + Math.round(melodie.getDurataMin() * 100.0) / 100.0;
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
