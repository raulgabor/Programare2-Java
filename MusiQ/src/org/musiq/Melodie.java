package org.musiq;

public class Melodie implements Comparable<Melodie>{
	
	private Integer id;
	private String numeMelodie;
	private String genMelodie;
	private Double durataMin;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNumeMelodie() {
		return numeMelodie;
	}
	public void setNumeMelodie(String numeMelodie) {
		this.numeMelodie = numeMelodie;
	}
	
	public String getGenMelodie() {
		return genMelodie;
	}
	public void setGenMelodie(String genMelodie) {
		this.genMelodie = genMelodie;
	}
	
	public Double getDurataMin() {
		return durataMin;
	}
	public void setDurataMin(Double durataMin) {
		this.durataMin = durataMin;
	}
	
	public Melodie(Integer id, String numeMelodie, String genMelodie, Double durataMin) {
		super();
		this.id = id;
		this.numeMelodie = numeMelodie;
		this.genMelodie = genMelodie;
		this.durataMin = durataMin;
	}
	
	public Melodie() {
		super();
	}
	
	@Override
	public int compareTo(Melodie o) {
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
		Melodie other = (Melodie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Melodie [id=" + id + ", numeMelodie=" + numeMelodie + ", genMelodie=" + genMelodie + ", durataMin="
				+ durataMin + "]";
	}
}
