package org.musiq;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestAlbum {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusiQ_JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		  
		List<Album> albume = new ArrayList<Album>(); 
		
		Artist a113 = (Artist) em.find(Artist.class, 113);
		albume.add(new Album(10, "Nume Album 10", a113, 2005));
		albume.add(new Album(11, "Nume Album 11", a113, 2006));
		
		Artist a115 = (Artist) em.find(Artist.class, 115);
		albume.add(new Album(12, "Nume Album 12", a115, 2004));
		albume.add(new Album(13, "Nume Album 13", a115, 2008));
		
		Artist a116 = (Artist) em.find(Artist.class, 116);
		albume.add(new Album(14, "Nume Album 14", a116, 2001));
		albume.add(new Album(15, "Nume Album 15", a116, 2003));
		albume.add(new Album(16, "Nume Album 16", a116, 1999));
		
		em.persist(albume.get(0));
		em.persist(albume.get(1));
		em.persist(albume.get(2));
		em.persist(albume.get(3));
		em.persist(albume.get(4));
		em.persist(albume.get(5));
		em.persist(albume.get(6));
		em.getTransaction().commit();
		 
		List<Album> lstAlbumePersistente = em.createQuery("SELECT al FROM Album al").getResultList();
		System.out.println("Lista albumelor persistente/salvate in baza de date");
		for (Album al : lstAlbumePersistente)
			System.out.println("Id: " + al.getId() + ", nume album: " + al.getNumeAlbum() + ", artist: "
					+ al.getArtist().getNumeScena() + ", an lansare: " + al.getAnLansare());
		
		em.getTransaction().begin();
		Album al11 = (Album) em.find(Album.class, 11);
		if(al11 != null) {
			al11.setNumeAlbum("Nume Album 11 revizuit");
			em.persist(al11);
		}
		
		Album al16 = (Album) em.createQuery("SELECT o FROM Album o WHERE o.id = 16").getSingleResult();
		if (al16 != null) em.remove(al16);
		
		em.getTransaction().commit();
		
		lstAlbumePersistente = em.createQuery("SELECT al FROM Album al").getResultList();
		System.out.println("Lista finala a albumelor persistente/salvate in baza de date");
		for (Album al : lstAlbumePersistente)
			System.out.println("Id: " + al.getId() + ", nume album: " + al.getNumeAlbum() + ", artist: "
					+ al.getArtist().getNumeScena() + ", an lansare: " + al.getAnLansare());
	}

}
