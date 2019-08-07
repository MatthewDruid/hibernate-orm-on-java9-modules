package com.example;

import java.util.Optional;
import java.util.Properties;
import java.util.ServiceLoader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;

import com.example.persistence.TestPersistenceUnitInfo;

public class Main {

	public static void main(String[] args) {
		ServiceLoader<PersistenceProvider> loader = ServiceLoader.load(PersistenceProvider.class);

		Optional<PersistenceProvider> optionalProvider = loader.findFirst();

		if (optionalProvider.isEmpty()) {
			System.err.println("No javax.persistence.spi.PersistenceProvider found");
			System.exit(-1);
		}

		PersistenceProvider provider = optionalProvider.get();

		PersistenceUnitInfo persistenceUnitInfo = new TestPersistenceUnitInfo(provider.getClass().getName());
		Properties map = new Properties();

		map.clear();
//		map.setProperty("javax.persistence.schema-generation.scripts.action", "drop-and-create");
//		map.setProperty("javax.persistence.schema-generation.scripts.create-target", "./create.sql");
//		map.setProperty("javax.persistence.schema-generation.scripts.drop-target", "./drop.sql");
		map.setProperty("javax.persistence.schema-generation.database.action", "create");
		provider.generateSchema(persistenceUnitInfo, map);

		map.clear();
		EntityManagerFactory entityManagerFactory = provider.createContainerEntityManagerFactory(persistenceUnitInfo, map);

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Person person = new Person( 1, "Bob", new Address( 1, "Main Street" ) );
		entityManager.persist( person );

		entityManager.getTransaction().commit();
		entityManager.close();

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
  
		Person bob = entityManager.find( Person.class, 1L );
  
		assert bob != null;
		assert bob.getName().equals( "Bob" );
		assert bob.getAddress().getName().equals( "Main Street" );
  
		entityManager.getTransaction().commit();
		entityManager.close();
  
		entityManagerFactory.close();
	}
}
