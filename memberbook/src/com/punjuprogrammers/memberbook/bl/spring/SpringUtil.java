package com.punjuprogrammers.memberbook.bl.spring;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class SpringUtil {
	public static ApplicationContext startApplication() {
		System.out.println("*********CDI Starting********");
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		dmds.setUsername("SA");
		dmds.setUrl("jdbc:hsqldb:hsql://localhost/memberbook");
		try {
			dmds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StaticApplicationContext parentCtx = new StaticApplicationContext();
		parentCtx.refresh();
		parentCtx.getBeanFactory().registerSingleton("dataSource", dmds);

		String[] paths = new String[]{"/WebContent/WEB-INF/applicationContextForJ2S.xml"};
		ApplicationContext ctx = new FileSystemXmlApplicationContext(paths);
		System.out.println("*********CDI Started********");
		
		return ctx;
	}
}
