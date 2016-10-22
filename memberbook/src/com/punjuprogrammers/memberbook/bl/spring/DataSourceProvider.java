package com.punjuprogrammers.memberbook.bl.spring;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

@Configuration
public class DataSourceProvider {

	private static final Logger LOG = LoggerFactory.getLogger(DataSourceProvider.class);

	@Bean
	public DataSource dataSource() {
		System.out.println("Getting data source");
		DataSource dataSource = null;
		JndiTemplate jndi = new JndiTemplate();
		try {
			dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/memberbook");
		} catch (NamingException e) {
			LOG.error("NamingException for java:comp/env/jdbc/memberbook", e);
		}
		return dataSource;
	}
}
