package org.musiq;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestMelodie {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusiQ_JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		  
		List<Melodie> melodii = new ArrayList<Melodie>(); 
		
		Album al10 = (Album) em.find(Album.class, 10);
		melodii.add(new Melodie(1, "Nume Melodie 1", al10, "Gen 1", 2.5));
		melodii.add(new Melodie(2, "Nume Melodie 2", al10, "Gen 2", 3.0));
		melodii.add(new Melodie(3, "Nume Melodie 3", al10, "Gen 3", 1.1));
		
		Album al11 = (Album) em.find(Album.class, 11);
		melodii.add(new Melodie(4, "Nume Melodie 4", al11, "Gen 4", 2.6));
		melodii.add(new Melodie(5, "Nume Melodie 5", al11, "Gen 5", 3.2));
		melodii.add(new Melodie(6, "Nume Melodie 6", al11, "Gen 6", 4.7));
		
		Album al12 = (Album) em.find(Album.class, 12);
		melodii.add(new Melodie(7, "Nume Melodie 7", al12, "Gen 7", 1.6));
		melodii.add(new Melodie(8, "Nume Melodie 8", al12, "Gen 8", 4.2));
		melodii.add(new Melodie(9, "Nume Melodie 9", al12, "Gen 9", 0.7));
		
		Album al13 = (Album) em.find(Album.class, 13);
		melodii.add(new Melodie(10, "Nume Melodie 10", al13, "Gen 10", 10.5));
		melodii.add(new Melodie(11, "Nume Melodie 11", al13, "Gen 11", 6.4));
		melodii.add(new Melodie(12, "Nume Melodie 12", al13, "Gen 12", 3.7));
		
		Album al14 = (Album) em.find(Album.class, 14);
		melodii.add(new Melodie(13, "Nume Melodie 13", al14, "Gen 13", 8.1));
		melodii.add(new Melodie(14, "Nume Melodie 14", al14, "Gen 14", 9.2));
		melodii.add(new Melodie(15, "Nume Melodie 15", al14, "Gen 15", 2.0));
		
		Album al15 = (Album) em.find(Album.class, 15);
		melodii.add(new Melodie(16, "Nume Melodie 16", al15, "Gen 16", 3.3));
		melodii.add(new Melodie(17, "Nume Melodie 17", al15, "Gen 17", 4.5));
		melodii.add(new Melodie(18, "Nume Melodie 18", al15, "Gen 18", 1.2));
		melodii.add(new Melodie(19, "Nume Melodie 19", al15, "Gen 19", 6.1));
		
		em.persist(melodii.get(0));
		em.persist(melodii.get(1));
		em.persist(melodii.get(2));
		em.persist(melodii.get(3));
		em.persist(melodii.get(4));
		em.persist(melodii.get(5));
		em.persist(melodii.get(6));
		em.persist(melodii.get(7));
		em.persist(melodii.get(8));
		em.persist(melodii.get(9));
		em.persist(melodii.get(10));
		em.persist(melodii.get(11));
		em.persist(melodii.get(12));
		em.persist(melodii.get(13));
		em.persist(melodii.get(14));
		em.persist(melodii.get(15));
		em.persist(melodii.get(16));
		em.persist(melodii.get(17));
		em.persist(melodii.get(18));
		em.getTransaction().commit();
		
		List<Melodie> lstMelodiiPersistente = em.createQuery("SELECT m FROM Melodie m").getResultList();
		System.out.println("Lista melodiilor persistente/salvate in baza de date");
		for (Melodie m : lstMelodiiPersistente)
			System.out.println("Id: " + m.getId() + ", nume album: " + m.getNumeMelodie() + ", album: "
					+ m.getAlbum().getNumeAlbum() + ", gen melodie: " + m.getGenMelodie() + ", durata min: "
					+ m.getDurataMin());
		
		em.getTransaction().begin();
		Melodie m11 = (Melodie) em.find(Melodie.class, 11);
		if(m11 != null) {
			m11.setNumeMelodie("Nume Melodie 11 revizuit");
			em.persist(m11);
		}
		
		Melodie m19 = (Melodie) em.createQuery("SELECT o FROM Melodie o WHERE o.id = 19").getSingleResult();
		if (m19 != null) em.remove(m19);
		
		em.getTransaction().commit();
		
		lstMelodiiPersistente = em.createQuery("SELECT m FROM Melodie m").getResultList();
		System.out.println("Lista finala a melodiilor persistente/salvate in baza de date");
		for (Melodie m : lstMelodiiPersistente)
			System.out.println("Id: " + m.getId() + ", nume album: " + m.getNumeMelodie() + ", album: "
					+ m.getAlbum().getNumeAlbum() + ", gen melodie: " + m.getGenMelodie() + ", durata min: "
					+ m.getDurataMin());
	}

}
