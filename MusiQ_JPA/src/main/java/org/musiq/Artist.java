package org.musiq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;

@Entity  
public class Artist extends Persoana{
	
	private String numeScena;
	private Integer anDebut;
	
	@OneToMany(mappedBy = "artist", cascade = ALL)
	private List<Album> albume = new ArrayList<Album>();
	
	public String getNumeScena() {
		return numeScena;
	}
	public void setNumeScena(String numeScena) {
		this.numeScena = numeScena;
	}
	
	public Integer getAnDebut() {
		return anDebut;
	}
	public void setAnDebut(Integer anDebut) {
		this.anDebut = anDebut;
	}
	
	public List<Album> getAlbume() {
		return albume;
	}
	public void setAlbume(List<Album> albume) {
		this.albume = albume;
	}
	
	public Integer getNumarAlbumeLansate() {
		if (albume.isEmpty()) return null;
	    
		Integer total = 0;
		
		total = total + this.albume.size();
		
		return total;
	}
	
	public Artist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Artist(Integer id, String numePersoana, Date dataNasterii, String nationalitate) {
		super(id, numePersoana, dataNasterii, nationalitate);
	}
	
	public Artist(Integer id, String numePersoana, Date dataNasterii, String nationalitate, String numeScena,
			Integer anDebut) {
		super(id, numePersoana, dataNasterii, nationalitate);
		this.numeScena = numeScena;
		this.anDebut = anDebut;
	}
	
	public Artist(Integer id, String numePersoana, Date dataNasterii, String nationalitate, String numeScena,
			Integer anDebut, List<Album> albume) {
		super(id, numePersoana, dataNasterii, nationalitate);
		this.numeScena = numeScena;
		this.anDebut = anDebut;
		this.albume = albume;
	}
	@Override
	public String toString() {
		return "Artist [numeScena=" + numeScena + ", anDebut=" + anDebut + ", albume=" + albume
				+ ", Numar de albume lansate=" + getNumarAlbumeLansate() + "]";
	}
}
