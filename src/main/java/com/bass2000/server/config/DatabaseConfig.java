package com.bass2000.server.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.bass2000.server.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("com.bass2000.server")
public class DatabaseConfig {

    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSourceBean());
        entityManager.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setJpaProperties(getHibernateProperties());

        return entityManager;
    }


    @Bean
    public DataSource dataSourceBean() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(env.getRequiredProperty("db.url"));
        ds.setDriverClassName(env.getRequiredProperty("db.driver"));
        ds.setUsername(env.getRequiredProperty("db.username"));
        ds.setPassword(env.getRequiredProperty("db.password"));

        return ds;
    }

    private Properties getHibernateProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hibernate.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find 'hibernate.properties' file in classpath!", e);
        }

    }
}
