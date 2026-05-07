package br.com.c137.project.sellmotors.authcommand.flyway;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.config.MasterDatabaseConfigProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static org.flywaydb.core.api.MigrationVersion.LATEST;

@Configuration
@RequiredArgsConstructor
public class FlywayConfig {

    @Autowired
    private MasterDatabaseConfigProperties masterDatabaseConfigProperties;

    @PostConstruct
    public void migrateFlyway() {
        Flyway metadataDbMigration = Flyway.configure()
                .dataSource(masterDatabaseConfigProperties.getUrl(), masterDatabaseConfigProperties.getUsername(),
                        masterDatabaseConfigProperties.getPassword())
                .locations("classpath:db/migrations/mastertenant").baselineOnMigrate(true).target(LATEST).load();
        metadataDbMigration.migrate();
    }
}
