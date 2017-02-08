package hei2017.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by pic on 08/02/2017.
 */

@Configuration
@EnableJpaRepositories(basePackages = "hei2017.dao")
public class DBConfig {

    final private String HEROKU_DATABASE_URL = "DATABASE_URL";
    final private String HEROKU_DATABASE_USR = "JDBC_DATABASE_USERNAME";
    final private String HEROKU_DATABASE_PWD = "JDBC_DATABASE_PASSWORD";

    @Bean
    public DataSource dataSource(Properties dbProperties)
    {
        String username = "";
        username = dbProperties.getProperty("username");

        if(null!=System.getenv(HEROKU_DATABASE_USR)){
            username = System.getenv(HEROKU_DATABASE_USR);
        }

        String password = "";
        password = dbProperties.getProperty("password");

        if(null!=System.getenv(HEROKU_DATABASE_PWD)){
            password = System.getenv(HEROKU_DATABASE_PWD);
        }

        String jdbcUrl = "";
        jdbcUrl = dbProperties.getProperty("jdbcUrl");

        if(null!=System.getenv(HEROKU_DATABASE_URL)){
            jdbcUrl = "jdbc:"+System.getenv(HEROKU_DATABASE_URL);
        }

        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(dbProperties.getProperty("driverClass"));
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setIdleConnectionTestPeriodInMinutes(60);
        dataSource.setIdleMaxAgeInMinutes(240);
        dataSource.setMaxConnectionsPerPartition(10);
        dataSource.setMinConnectionsPerPartition(1);
        dataSource.setPartitionCount(2);
        dataSource.setAcquireIncrement(5);
        dataSource.setStatementsCacheSize(500);
        return dataSource;
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory)
    {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory, DataSource dataSource)
    {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) throws SQLException
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        factory.setPackagesToScan("hei2017.entity");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
