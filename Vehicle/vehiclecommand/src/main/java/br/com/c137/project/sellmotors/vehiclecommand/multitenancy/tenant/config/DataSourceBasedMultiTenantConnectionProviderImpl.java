package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.java.Log;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
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

    // Identificador padrão caso nenhum seja informado
    private final String defaultTenant = "sell_motors_vehicles";
    private static final String NOMEBANCO = "user_";

    private final Cache<Object, DataSource> dataSourcesMtApp = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .removalListener((Object key, DataSource dataSource, RemovalCause cause) -> {
                if (dataSource instanceof HikariDataSource hds) {
                    hds.close();
                }
            })
            .build();

    /**
     * O Hibernate chama este método para validações iniciais ou operações fora de escopo de tenant.
     * Agora ele tenta buscar o tenant padrão dinamicamente.
     */
    @Override
    protected DataSource selectAnyDataSource() {
        return selectDataSource(defaultTenant);
    }

    @Override
    protected DataSource selectDataSource(Object tenantIdentifier) {
        if (tenantIdentifier == null) {
            throw new BadCredentialsException("invalid-argument-tenant");
        }

        return dataSourcesMtApp.get(tenantIdentifier, key -> {
            String tenantStr = (String) key;

            // Lógica para lidar com o tenant padrão ou master que agora é dinâmico
            if (defaultTenant.equals(tenantStr)) {
                // Se o master tiver um UUID fixo ou lógica diferente, ajuste aqui.
                // Caso contrário, ele seguirá para a criação via DataSourceUtil.
                log.info("Criando DataSource para o tenant padrão: " + tenantStr);
            }

            try {
                // Remove o prefixo para obter o UUID
                String uuidRaw = tenantStr.replace(NOMEBANCO, "");
                UUID dbUserId = UUID.fromString(uuidRaw);
                return DataSourceUtil.createAndConfigureDataSource(dbUserId, ip, port);
            } catch (IllegalArgumentException e) {
                log.severe("Erro ao formatar UUID para o tenant: " + tenantStr);
                throw new BadCredentialsException("invalid-tenant-id-format");
            }
        });
    }
}