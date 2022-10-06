package org.musiq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utilizator extends Persoana{

	private String nickname;
	private List<Melodie> melodiiPreferate = new ArrayList<Melodie>();
	private List<Album> albumePreferate = new ArrayList<Album>();
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public List<Melodie> getMelodiiPreferate() {
		return melodiiPreferate;
	}
	public void setMelodiiPreferate(List<Melodie> melodiiPreferate) {
		this.melodiiPreferate = melodiiPreferate;
	}
	
	public List<Album> getAlbumePreferate() {
		return albumePreferate;
	}
	public void setAlbumePreferate(List<Album> albumePreferate) {
		this.albumePreferate = albumePreferate;
	}
	
	public String getInterval() {
		if(melodiiPreferate.isEmpty() && albumePreferate.isEmpty()) return null;
		
		String ocazional = "ascultator ocazional!";
		String mediu = "ascultator mediu!";
		String pasionat = "ascultator pasionat!";
		
		if (this.melodiiPreferate.size() <= 10 || this.albumePreferate.size() <= 10) return ocazional;
		else if((this.melodiiPreferate.size() > 10 && this.melodiiPreferate.size() <= 25) || 
				(this.albumePreferate.size() > 10 && this.albumePreferate.size() < 25)) return mediu;
		else return pasionat;
	}
	
	public Utilizator(Integer id, String numePersoana, Date dataNasterii, String nationalitate) {
		super(id, numePersoana, dataNasterii, nationalitate);
	}
	
	public Utilizator(Integer id, String numePersoana, Date dataNasterii, String nationalitate, String nickname) {
		super(id, numePersoana, dataNasterii, nationalitate);
		this.nickname = nickname;
	}
	
	public Utilizator(Integer id, String numePersoana, Date dataNasterii, String nationalitate, String nickname,
			List<Melodie> melodiiPreferate, List<Album> albumePreferate) {
		super(id, numePersoana, dataNasterii, nationalitate);
		this.nickname = nickname;
		this.melodiiPreferate = melodiiPreferate;
		this.albumePreferate = albumePreferate;
	}
	@Override
	public String toString() {
		return "Utilizator [nickname=" + nickname + ", melodiiPreferate=" + melodiiPreferate + ", albumePreferate="
				+ albumePreferate + ", Utilizatorul este un " + getInterval() + "]";
	}	
	
	
}
