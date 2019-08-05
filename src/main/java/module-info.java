
module com.example {
    requires java.persistence;
    requires org.hibernate.orm.core;
	requires java.sql;

    opens com.example to org.hibernate.orm.core;

	uses javax.persistence.spi.PersistenceProvider;
}
