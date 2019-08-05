package com.example;

import java.util.HashMap;
import java.util.Optional;
import java.util.ServiceLoader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;

import com.example.persistence.TestPersistenceUnitInfo;

public class Main {

    public static void main(String[] args) {
		ServiceLoader<PersistenceProvider> loader = ServiceLoader.load(PersistenceProvider.class);

		Optional<PersistenceProvider> optionalProvider = loader.findFirst();

		if (optionalProvider.isEmpty()) {
			System.err.println("No javax.persistence.spi.PersistenceProvider found");
			System.exit(-1);
		}

        EntityManagerFactory entityManagerFactory = optionalProvider.get().createContainerEntityManagerFactory(new TestPersistenceUnitInfo(), new HashMap<String, String>());

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
