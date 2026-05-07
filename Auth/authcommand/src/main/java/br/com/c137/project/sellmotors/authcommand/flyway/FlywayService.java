package br.com.c137.project.sellmotors.authcommand.flyway;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.DatabaseStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models.UserTenant;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.repositories.UserTenantRepository;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.flywaydb.core.api.MigrationVersion.LATEST;

@Service
public class FlywayService {

	@Value("${db.server.ip}")
	private String ip;

	@Value("${db.server.port}")
	private String port;

	@Autowired
	private UserTenantRepository userTenantRepository;
	
	public void createTenant(UserTenant userTenant) {
		criarSchemasNoBanco(
				userTenant,
				"jdbc:postgresql://"+ip+":"+port+"/sell_motors_vehicles?sslmode=disable&options=-c%20timezone=America/Sao_Paulo",
				"classpath:db/migrations/tenant/vehicles");
		criarSchemasNoBanco(
				userTenant,
				"jdbc:postgresql://"+ip+":"+port+"/sell_motors_leads?sslmode=disable&options=-c%20timezone=America/Sao_Paulo",
				"classpath:db/migrations/tenant/leads");
		
		if(userTenant.getDatabaseStatus().equals(DatabaseStatus.NOT_CREATED)) {
			userTenantRepository.updateDatabaseStatus(DatabaseStatus.CREATED, userTenant.getId());
		}
	}

	private void criarSchemasNoBanco(UserTenant userTenant, String url, String localMigrations){
		Flyway userTenantDbMigration = Flyway.configure()
				.dataSource(url, userTenant.getUserName(), userTenant.getPassword())
				.locations(localMigrations).target(LATEST).baselineOnMigrate(true)
				.defaultSchema(userTenant.getDbName()).load();
		userTenantDbMigration.migrate();
	}

}
