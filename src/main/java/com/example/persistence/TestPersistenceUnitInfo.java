package com.example.persistence;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

public class TestPersistenceUnitInfo implements PersistenceUnitInfo {

	private static String JPA_VERSION = "2.2";
	private String persistenceUnitName = "testPU";
	private PersistenceUnitTransactionType transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;
	private List<String> managedClassNames = new ArrayList<String>();
	private List<String> mappingFileNames = new ArrayList<String>();
	private Properties properties;
	private DataSource jtaDataSource;
	private DataSource nonjtaDataSource;
	private List<ClassTransformer> transformers = new ArrayList<ClassTransformer>();

	public TestPersistenceUnitInfo() {
		this.managedClassNames.add("com.example.Address");
		this.managedClassNames.add("com.example.Persion");

		this.properties = new Properties();
		this.properties.put("javax.persistence.jdbc.driver", "org.hsqldb.jdbc.JDBCDriver");
		this.properties.put("javax.persistence.jdbc.url", "jdbc:hsqldb:mem:myDB");
		this.properties.put("javax.persistence.jdbc.user", "matthewdruid");
	}

	@Override
	public void addTransformer(ClassTransformer arg0) {
	}

	@Override
	public boolean excludeUnlistedClasses() {
		return true;
	}

	@Override
	public ClassLoader getClassLoader() {
		return null;
	}

	@Override
	public List<URL> getJarFileUrls() {
		return null;
	}

	@Override
	public DataSource getJtaDataSource() {
		return this.jtaDataSource;
	}

	@Override
	public List<String> getManagedClassNames() {
		return null;
	}

	@Override
	public List<String> getMappingFileNames() {
		return this.mappingFileNames;
	}

	@Override
	public ClassLoader getNewTempClassLoader() {
		return null;
	}

	@Override
	public DataSource getNonJtaDataSource() {
		return this.nonjtaDataSource;
	}

	@Override
	public String getPersistenceProviderClassName() {
		return null;
	}

	@Override
	public String getPersistenceUnitName() {
		return this.persistenceUnitName;
	}

	@Override
	public URL getPersistenceUnitRootUrl() {
		return null;
	}

	@Override
	public String getPersistenceXMLSchemaVersion() {
		return JPA_VERSION;
	}

	@Override
	public Properties getProperties() {
		return this.properties;
	}

	@Override
	public SharedCacheMode getSharedCacheMode() {
		return null;
	}

	@Override
	public PersistenceUnitTransactionType getTransactionType() {
		return this.transactionType;
	}

	@Override
	public ValidationMode getValidationMode() {
		return null;
	}
}

