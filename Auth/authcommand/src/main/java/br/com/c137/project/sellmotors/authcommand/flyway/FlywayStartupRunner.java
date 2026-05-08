package br.com.c137.project.sellmotors.authcommand.flyway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FlywayStartupRunner implements ApplicationRunner {

    private final Flyway masterTenantFlyway;
    private final FlywayService flywayService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Iniciando migração Flyway do banco master");
        masterTenantFlyway.migrate();
        log.info("Migração Flyway do banco master finalizada");

        log.info("Iniciando migração Flyway dos tenants");
        flywayService.migrateAllTenants();
        log.info("Migração Flyway dos tenants finalizada");
    }
}
