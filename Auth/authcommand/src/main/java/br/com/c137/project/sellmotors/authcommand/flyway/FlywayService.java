package br.com.c137.project.sellmotors.authcommand.flyway;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.DatabaseStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models.UserTenant;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.repositories.UserTenantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlywayService {

	private static final String VEHICLES_DATABASE = "sell_motors_vehicles";
	private static final String LEADS_DATABASE = "sell_motors_leads";
	private static final String VEHICLES_MIGRATIONS = "classpath:db/migrations/tenant/vehicles";
	private static final String LEADS_MIGRATIONS = "classpath:db/migrations/tenant/leads";

	@Value("${db.server.ip}")
	private String ip;

	@Value("${db.server.port}")
	private String port;

	private final UserTenantRepository userTenantRepository;

	public void createTenant(UserTenant userTenant) {
		migrateTenantDatabases(userTenant);

		if (DatabaseStatus.NOT_CREATED.equals(userTenant.getDatabaseStatus())) {
			userTenantRepository.updateDatabaseStatus(DatabaseStatus.CREATED, userTenant.getId());
		}
	}

	public void migrateAllTenants() {
		List<UserTenant> tenants = userTenantRepository.findByDatabaseStatus(DatabaseStatus.CREATED);

		log.info("Foram encontrados {} tenants com status CREATED para migração", tenants.size());

		for (UserTenant tenant : tenants) {
			try {
				migrateTenantDatabases(tenant);
				log.info("Migrações concluídas para tenant {}", tenant.getDbName());
			} catch (Exception ex) {
				log.error("Falha ao migrar tenant {}. A aplicação continuará migrando os demais tenants.", tenant.getDbName(), ex);
			}
		}
	}

	private void migrateTenantDatabases(UserTenant userTenant) {
		migrateDatabase(userTenant, buildJdbcUrl(VEHICLES_DATABASE), VEHICLES_MIGRATIONS);
		migrateDatabase(userTenant, buildJdbcUrl(LEADS_DATABASE), LEADS_MIGRATIONS);
	}

	private void migrateDatabase(UserTenant userTenant, String url, String migrationsLocation) {
		try {
			Flyway flyway = Flyway.configure()
					.dataSource(url, userTenant.getUserName(), userTenant.getPassword())
					.locations(migrationsLocation)
					.defaultSchema(userTenant.getDbName())
					.schemas(userTenant.getDbName())
					.baselineOnMigrate(true)
					.load();

			flyway.migrate();

			log.info("Migração executada com sucesso. Tenant={}, url={}, schema={}, location={}",
					userTenant.getDbName(), url, userTenant.getDbName(), migrationsLocation);
		} catch (FlywayException ex) {
			throw new IllegalStateException(
					String.format(
							"Erro ao executar Flyway. Tenant=%s, schema=%s, location=%s, url=%s",
							userTenant.getDbName(),
							userTenant.getDbName(),
							migrationsLocation,
							url
					),
					ex
			);
		}
	}

	private String buildJdbcUrl(String databaseName) {
		return String.format(
				"jdbc:postgresql://%s:%s/%s?sslmode=disable&options=-c%%20timezone=America/Sao_Paulo",
				ip,
				port,
				databaseName
		);
	}

}
