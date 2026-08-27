package com.ledgerhouse.ledger;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    @SuppressWarnings("resource")
    PostgreSQLContainer postgresContainer() {
        return new PostgreSQLContainer(DockerImageName.parse("postgres:18.6"))
                .withDatabaseName("ledger_house")
                .withUsername("ledger_app")
                .withPassword("ledger_test")
                .withInitScript("db/test/init-ledger-schema.sql");
    }
}
