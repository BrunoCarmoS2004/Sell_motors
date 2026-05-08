package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.config;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.UUID;

public final class DataSourceUtil {

    public static DataSource createAndConfigureDataSource(UUID dbUserId, String ip, String port) {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername("sellmotors");
        ds.setPassword("sellmotors");
        ds.setJdbcUrl("jdbc:postgresql://"+ip+":"+port+"/sell_motors_vehicles");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(0);
        ds.setIdleTimeout(3000);
        ds.setConnectionTimeout(10000);
        ds.setMaxLifetime(60000);
        String tenantConnectionPoolName = "user_"+dbUserId.toString() + "-connection-pool";
        ds.setPoolName(tenantConnectionPoolName);
        String schemaName = "user_" + dbUserId.toString();
        ds.setSchema(schemaName);
        return ds;
    }

}