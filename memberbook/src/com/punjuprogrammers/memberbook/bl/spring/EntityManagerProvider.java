package com.punjuprogrammers.memberbook.bl.spring;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EntityManagerProvider {

	private static final Logger LOG = LoggerFactory.getLogger(EntityManagerProvider.class);

	// @PersistenceContext(unitName = "memberbookLocal")
	private EntityManager entityManager;

	@Bean
	public Properties hibernateProperties() {
		final Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show-sql", "true");
		//properties.put("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver");
		//properties.put("hibernate.connection.url", "jdbc:hsqldb:hsql://localhost/memberbook;shutdown=true");
		//properties.put("hibernate.connection.username", "SA");
		//properties.put("hibernate.connection.password", "");
		return properties;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties) {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		System.out.println("entityManagerFactory");
		HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
		jpaAdapter.setDatabase(Database.HSQL);
		jpaAdapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.punjuprogrammers");
		em.setJpaVendorAdapter(jpaAdapter);
		em.setJpaProperties(hibernateProperties);
		em.setPersistenceUnitName("memberbookLocal");
		em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		em.afterPropertiesSet();

		return em.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		LOG.info("Get transactionManager");
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}
}
