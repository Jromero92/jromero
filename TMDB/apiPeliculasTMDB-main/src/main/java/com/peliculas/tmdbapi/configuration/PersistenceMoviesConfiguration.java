package com.peliculas.tmdbapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableTransactionManagement
@EnableJpaRepositories(
        //basePackageClasses = MovieEntity.class,
        entityManagerFactoryRef = "movieEntityManagerFactory",
        transactionManagerRef = "movieTransactionManager",
        basePackages = "com.peliculas.tmdbapi.repository.movies"
)
public class PersistenceMoviesConfiguration {

    @Autowired
    private Environment env;

    @Primary
    @Bean(name="movieDataSource")
    public DataSource movieDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("movies.datasource.url"));
        dataSource.setUsername(env.getProperty("movies.datasource.username"));
        dataSource.setPassword(env.getProperty("movies.datasource.password"));
        //dataSource.setDriverClassName(env.getProperty("movies.datasource.driver-class-name"));

        return dataSource;
    }

    @Primary
    @Bean(name= "movieEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource((movieDataSource()));
        em.setPackagesToScan("com.peliculas.tmdbapi.entities.movies");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("movies.jpa.datasource.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("movies.jpa.datasource.database-platform"));
        //properties.put( "spring.jpa.properties.hibernate.globally_quoted_identifiers", env.getProperty("spring.jpa.properties.hibernate.globally_quoted_identifiers"));

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean(name= "movieTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
