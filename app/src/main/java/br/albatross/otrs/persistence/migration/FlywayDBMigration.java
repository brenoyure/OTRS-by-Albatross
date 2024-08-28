package br.albatross.otrs.persistence.migration;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;

@Startup @Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class FlywayDBMigration {

    @Resource(lookup = "java:jboss/datasources/ProblemaDS")
    private DataSource dataSource;

    @PostConstruct
    public void migrate() {
        Flyway
            .configure()
            .dataSource(dataSource)
            .locations("classpath:/flyway/db/migration")
            .load()
            .migrate();

    }

}
