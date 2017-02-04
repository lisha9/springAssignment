package com.model;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class PersonJpa {

private static EntityManager em;
	
public PersonJpa() {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
    em = emf.createEntityManager();
}

	public void insert(Person p)
	{
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
	}
	
	public ArrayList<Person> selectStar()
	{
		  	TypedQuery<Person> query =
		  	em.createQuery("SELECT p FROM Person p", Person.class);
		  	ArrayList<Person> results = (ArrayList<Person>) query.getResultList();
		
			return results;
	}
	
	
	public void delete(int id)
	{
		em.getTransaction().begin( );
        Person person = em.find(Person.class, id );
        em.remove(person);
        em.getTransaction().commit();
        em.close();    
	}
	
	public void edit(Person p)
	{
		em.getTransaction().begin( );
        Person person = em.find(Person.class, p.getPersonid() );
        person.setName(p.getName());
        person.setCountry(p.getCountry());
        em.getTransaction().commit();
        em.close();    
	}

	public Person getSinglePerson(int id) {
		em.getTransaction().begin( );
        Person p = em.find(Person.class, id );
        em.close();
				return p;
		
	}
	
}
