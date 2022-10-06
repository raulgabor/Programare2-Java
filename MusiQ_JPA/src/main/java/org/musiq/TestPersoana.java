package org.musiq;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import java.util.Date;

public class TestPersoana {

	@SuppressWarnings({ "unchecked", "deprecation"})
	public static void main(String[] args) {
		
		List<Persoana> persoane = new ArrayList<Persoana>();
		persoane.add(new Persoana(101, "Nume 1", new Date(99,10,10), "Nationalitate 1"));
		persoane.add(new Persoana(102, "Nume 2", new Date(99,01,20), "Nationalitate 2"));
		persoane.add(new Persoana(103, "Nume 3", new Date(89,01,31), "Nationalitate 3"));
		persoane.add(new Persoana(104, "Nume 4", new Date(100,11,9), "Nationalitate 4"));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusiQ_JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(persoane.get(0));
		em.persist(persoane.get(1));
		em.persist(persoane.get(2));
		em.persist(persoane.get(3));
		em.getTransaction().commit();
		
		List<Persoana> lstPersoanePersistente = em.createQuery("SELECT p FROM Persoana p").getResultList();
		System.out.println("Lista persoane persistente/salvate in baza de date");
		for(Persoana p: lstPersoanePersistente)
			System.out.println("Id: " + p.getId() + ", nume: " + p.getNumePersoana() + ", data nasterii: " +
		    p.getDataNasterii() + ", nationalitate: " + p.getNationalitate());
		
		em.getTransaction().begin();
		Persoana p102 = em.find(Persoana.class, 102);
		if(p102 != null) {
			p102.setNumePersoana("Nume 2 Revizuit");
			em.persist(p102);
		}
		
		Persoana p103 = (Persoana) em.createQuery("SELECT o FROM Persoana o WHERE o.id = 103").getSingleResult();
		if (p103 != null) em.remove(p103);
		
		em.getTransaction().commit();
		
		lstPersoanePersistente = em.createQuery("SELECT p FROM Persoana p").getResultList();
		System.out.println("Lista finala persoane persistente/salvate in baza de date");
		for(Persoana p: lstPersoanePersistente)
			System.out.println("Id: " + p.getId() + ", nume: " + p.getNumePersoana() + ", data nasterii: " +
		    p.getDataNasterii() + ", nationalitate: " + p.getNationalitate());
		}
		
	}


