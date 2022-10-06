package org.musiq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestArtist {
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void main(String[] args) {
	
		List<Artist> artisti = new ArrayList<Artist>();
		artisti.add(new Artist(113, "Nume 13", new Date(85,10,10), "Nationalitate 13", "NumeScena 13", 1999));
		artisti.add(new Artist(114, "Nume 14", new Date(79,10,10), "Nationalitate 14", "NumeScena 14", 1989));
		artisti.add(new Artist(115, "Nume 15", new Date(86,10,10), "Nationalitate 15", "NumeScena 15", 2000));
		artisti.add(new Artist(116, "Nume 16", new Date(75,10,10), "Nationalitate 16", "NumeScena 16", 2001));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusiQ_JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(artisti.get(0));
		em.persist(artisti.get(1));
		em.persist(artisti.get(2));
		em.persist(artisti.get(3));
		em.getTransaction().commit();
		
		List<Artist> lstArtistiPersistenti = em.createQuery("SELECT a FROM Artist a").getResultList();
		System.out.println("Lista artistilor persistenti/salvati in baza de date");
		for(Artist a: lstArtistiPersistenti)
			System.out.println("Id: " + a.getId() + ", nume: " + a.getNumePersoana() + ", data nasterii: " +
		    a.getDataNasterii() + ", nationalitate: " + a.getNationalitate() + ", NumeScena: " + a.getNumeScena() +
		    ", AnDebut: " + a.getAnDebut());
		
		em.getTransaction().begin();
		Artist a113 = (Artist) em.find(Artist.class, 113);
		if(a113 != null) {
			a113.setNumeScena("NumeScena 13 revizuit");
			em.persist(a113);
		}
		
		Artist a114 = (Artist) em.createQuery("SELECT o FROM Artist o WHERE o.id = 114").getSingleResult();
		if (a114 != null) em.remove(a114);
		
		em.getTransaction().commit();
		
		lstArtistiPersistenti = em.createQuery("SELECT a FROM Artist a").getResultList();
		System.out.println("Lista artistilor persistenti/salvati in baza de date");
		for(Artist a: lstArtistiPersistenti)
			System.out.println("Id: " + a.getId() + ", nume: " + a.getNumePersoana() + ", data nasterii: " +
		    a.getDataNasterii() + ", nationalitate: " + a.getNationalitate() + ", NumeScena: " + a.getNumeScena() +
		    ", AnDebut: " + a.getAnDebut());
	}
}
