package com.ledgerhouse.ledger;

import static org.assertj.core.api.Assertions.assertThat;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class LedgerServiceApplicationTests {

    @Autowired private DSLContext dsl;

    @Test
    void flywayAppliesBaselineMigrationAndJooqIsAvailable() {
        Long migrationCount =
                dsl.fetchOne(
                                """
                                select count(*)
                                from ledger.flyway_schema_history
                                where version = '1'
                                  and success
                                """)
                        .get(0, Long.class);

        assertThat(migrationCount).isEqualTo(1L);
    }
}
