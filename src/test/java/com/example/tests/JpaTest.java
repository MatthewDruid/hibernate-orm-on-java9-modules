package com.example.tests;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.ServiceLoader;
import javax.persistence.spi.PersistenceProvider;

import javax.persistence.*;

import com.example.Address;
import com.example.Person;
import com.example.persistence.TestPersistenceUnitInfo;

public class JpaTest {

	@Test
	public void testServiceLoader() throws Exception {
		ServiceLoader<PersistenceProvider> loader = ServiceLoader.load(PersistenceProvider.class);

		Optional<PersistenceProvider> optionalProvider = loader.findFirst();

		Assertions.assertTrue(optionalProvider.isPresent());

		if (optionalProvider.isPresent()) {
			PersistenceProvider provider = optionalProvider.get();

			EntityManagerFactory factory = provider.createContainerEntityManagerFactory(new TestPersistenceUnitInfo(provider.getClass().getSimpleName()), new HashMap<String, Object>());
			
			EntityManager em = factory.createEntityManager();

			em.getTransaction().begin();
	
			//Person person = new Person(1, "TEST", new Address(1, "TEST"));
	
			//em.persist(person);
			
			Address address = new Address(1, "TEST");

			em.persist(address);
	
			em.getTransaction().commit();
	
			em.close();
		}
	}
}

