package org.musiq;


import java.util.Date;


import javax.persistence.Entity;


@Entity  
public class Utilizator extends Persoana{

	private String nickname;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Utilizator() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Utilizator(Integer id, String numePersoana, Date dataNasterii, String nationalitate) {
		super(id, numePersoana, dataNasterii, nationalitate);
	}
	
	public Utilizator(Integer id, String numePersoana, Date dataNasterii, String nationalitate, String nickname) {
		super(id, numePersoana, dataNasterii, nationalitate);
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "Utilizator [nickname=" + nickname + "]";
	}	
	
	
}
