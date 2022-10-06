package org.musiq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestUtilizator {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Utilizator> utilizatori = new ArrayList<Utilizator>();
		utilizatori.add(new Utilizator(109, "Nume 9", new Date(99,10,10), "Nationalitate 9", "Nickname 9"));
		utilizatori.add(new Utilizator(110, "Nume 10", new Date(72,11,10), "Nationalitate 10", "Nickname 10"));
		utilizatori.add(new Utilizator(111, "Nume 11", new Date(63,12,15), "Nationalitate 11", "Nickname 11"));
		utilizatori.add(new Utilizator(112, "Nume 12", new Date(91,01,21), "Nationalitate 12", "Nickname 12"));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusiQ_JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(utilizatori.get(0));
		em.persist(utilizatori.get(1));
		em.persist(utilizatori.get(2));
		em.persist(utilizatori.get(3));
		em.getTransaction().commit();
		
		List<Utilizator> lstUtilizatoriPersistenti = em.createQuery("SELECT u FROM Utilizator u").getResultList();
		System.out.println("Lista utilizatori persistenti/salvati in baza de date");
		for(Utilizator u: lstUtilizatoriPersistenti)
			System.out.println("Id: " + u.getId() + ", nume: " + u.getNumePersoana() + ", data nasterii: " +
		    u.getDataNasterii() + ", nationalitate: " + u.getNationalitate() + ", Nickname: " + u.getNickname());
		
		em.getTransaction().begin();
		Utilizator u109 = (Utilizator) em.find(Utilizator.class, 109);
		if(u109 != null) {
			u109.setNickname("Nickname 9 revizuit");
			em.persist(u109);
		}
		
		Utilizator u110 = (Utilizator) em.createQuery("SELECT o FROM Utilizator o WHERE o.id = 110").getSingleResult();
		if (u110 != null) em.remove(u110);
		
		em.getTransaction().commit();
		
		lstUtilizatoriPersistenti = em.createQuery("SELECT u FROM Utilizator u").getResultList();
		System.out.println("Lista finala utilizatori persistenti/salvati in baza de date");
		for(Utilizator u: lstUtilizatoriPersistenti)
			System.out.println("Id: " + u.getId() + ", nume: " + u.getNumePersoana() + ", data nasterii: " +
		    u.getDataNasterii() + ", nationalitate: " + u.getNationalitate() + ", Nickname: " + u.getNickname());
	
	}

}
