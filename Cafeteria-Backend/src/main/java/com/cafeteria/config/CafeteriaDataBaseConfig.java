package com.cafeteria.config;

import java.util.HashMap;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//Load to Environment
@PropertySources(@PropertySource("classpath:application.properties"))
public class CafeteriaDataBaseConfig {

	@Autowired
	private Environment env; // Contains Properties Load by @PropertySources

	@Bean
	public DataSource ds1Datasource() {

		PoolProperties p = new PoolProperties();
		p.setUrl(env.getProperty("spring.datasource.url"));
		p.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		p.setUsername(env.getProperty("spring.datasource.username"));
		p.setPassword(env.getProperty("spring.datasource.password"));
		p.setRemoveAbandonedTimeout(10000);
		p.setJmxEnabled(true);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(100);
		p.setInitialSize(10);
		p.setMaxWait(10000);
//		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

		DataSource datasource = new DataSource();
		datasource.setPoolProperties(p);
		return datasource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean ds1EntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(ds1Datasource());

		// Scan Entities in Package:
		em.setPackagesToScan(new String[] { Constants.PACKAGE_ENTITIES_1 });
		em.setPersistenceUnitName(Constants.JPA_UNIT_NAME_1);

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		em.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<>();

		// JPA & Hibernate
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));

		em.setJpaPropertyMap(properties);
		em.afterPropertiesSet();
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(ds1EntityManager().getObject());
		return transactionManager;
	}

}
