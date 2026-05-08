package br.com.c137.project.sellmotors.authcommand.flyway;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.config.MasterDatabaseConfigProperties;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FlywayConfig {

    private final MasterDatabaseConfigProperties masterDatabaseConfigProperties;

    @Bean
    public Flyway masterTenantFlyway() {
        return Flyway.configure()
                .dataSource(
                        masterDatabaseConfigProperties.getUrl(),
                        masterDatabaseConfigProperties.getUsername(),
                        masterDatabaseConfigProperties.getPassword()
                )
                .locations("classpath:db/migrations/mastertenant")
                .baselineOnMigrate(true)
                .load();
    }
}
