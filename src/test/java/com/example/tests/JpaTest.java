package com.example.tests;

import org.junit.jupiter.api.*;

import javax.persistence.*;

import com.example.Address;
import com.example.Person;

public class JpaTest {

	@Test
	public void findClassByName() throws Exception {
		//Object derby = Class.forName("org.apache.derby.jdbc.EmbeddedDriver").getDeclaredConstructor().newInstance();
		//Object postgresql = Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		Object hsqldb = Class.forName("org.hsqldb.jdbc.JDBCDriver").getDeclaredConstructor().newInstance();

		//System.out.println(derby.getClass().getName());
		//System.out.println(postgresql.getClass().getName());
		System.out.println(hsqldb.getClass().getName());
	}

	@Test
	public void setUp() throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("templatePU");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();

		Address address = new Address(1, "TEST");
		Person person = new Person(1, "TEST", address);

		em.persist(person);

		em.getTransaction().commit();

		em.close();
	}
}

