package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.config;

import br.com.c137.project.sellmotors.leadcommand.exceptions.TenantSchemaNotReadyException;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.mastertenant.enums.DatabaseStatus;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.mastertenant.models.UserTenant;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.mastertenant.repositories.UserTenantRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;

import javax.sql.DataSource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
@Log
public class DataSourceBasedMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    @Value("${db.server.ip}")
    private String ip;

    @Value("${db.server.port}")
    private String port;

    private final Cache<Object, DataSource> dataSourcesMtApp = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .removalListener((Object key, DataSource dataSource, RemovalCause cause) -> {
                if (dataSource instanceof HikariDataSource hds) {
                    hds.close();
                }
            })
            .build();

    @Autowired
    private UserTenantRepository userTenantRepository;

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    private String sellmotorsMasterDb = "sell_motors_leads";

    @PostConstruct
    public void loadDataSources() {
        dataSourcesMtApp.put(sellmotorsMasterDb, masterDataSource);
    }
    private static final String NOMEBANCO = "user_";

    @Override
    protected DataSource selectAnyDataSource() {
        return masterDataSource;
    }

    @Override
    protected DataSource selectDataSource(Object tenantIdentifier) {
        if (tenantIdentifier == null) {
            throw new BadCredentialsException("invalid-argument-tenant");
        }

        if (sellmotorsMasterDb.equals(tenantIdentifier)) {
            return masterDataSource;
        }

        return dataSourcesMtApp.get(tenantIdentifier, key -> {
            String tenant = (String) key;
            UUID dbUserId = UUID.fromString(tenant.replace(NOMEBANCO, ""));

            UserTenant novoTenant = userTenantRepository.findByDbUserId(dbUserId);

            if (novoTenant.getDatabaseStatus().equals(DatabaseStatus.NOT_CREATED)) {
                throw new TenantSchemaNotReadyException("Database not ready for tenant: " + tenant);
            }

            return DataSourceUtil.createAndConfigureDataSource(novoTenant, ip, port);
        });
    }
}
