package org.musiq;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

@Entity  
public class Persoana implements Comparable <Persoana>{
	
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer id;
	private String numePersoana;
	
	@Temporal(DATE)
	private Date dataNasterii;
	private String nationalitate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNumePersoana() {
		return numePersoana;
	}
	public void setNumePersoana(String numePersoana) {
		this.numePersoana = numePersoana;
	}
	
	public Date getDataNasterii() {
		return dataNasterii;
	}
	public void setDataNasterii(Date dataNasterii) {
		this.dataNasterii = dataNasterii;
	}
	
	public String getNationalitate() {
		return nationalitate;
	}
	public void setNationalitate(String nationalitate) {
		this.nationalitate = nationalitate;
	}
	
	public Persoana(Integer id, String numePersoana, Date dataNasterii, String nationalitate) {
		super();
		this.id = id;
		this.numePersoana = numePersoana;
		this.dataNasterii = dataNasterii;
		this.nationalitate = nationalitate;
	}
		
	public Persoana() {
		super();
	}
	
	@Override
	public int compareTo(Persoana o) {
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
		Persoana other = (Persoana) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Persoana [id=" + id + ", numePersoana=" + numePersoana + ", dataNasterii=" + dataNasterii
				+ ", nationalitate=" + nationalitate + "]";
	}	
}
