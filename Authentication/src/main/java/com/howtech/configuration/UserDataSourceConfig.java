package com.howtech.configuration;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.Configuration.Builder;
import org.neo4j.ogm.config.ConfigurationSource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.howtech")
@EnableNeo4jRepositories(basePackages="com.howtech.repository")
@EnableTransactionManagement
public class UserDataSourceConfig {
			
	public static final String URL =
			System.getenv("NEO4J_URL") != null ?
			System.getenv("NEO4J_URL") : "bolt://localhost:7687";
	@Bean
	public SessionFactory sessionFactory() {
		return new SessionFactory(getConfiguration(), "com.howtech.model");
	}
	
	@Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        ConfigurationSource properties = new ClasspathConfigurationSource("application.properties");
        org.neo4j.ogm.config.Configuration configuration = new Builder(properties).build();
        
        return configuration;
    }

	@Bean
	public Neo4jTransactionManager transactionManager() {
		return new Neo4jTransactionManager(sessionFactory());
	}
	
	
}
