package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.config;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.mastertenant.models.UserTenant;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public final class DataSourceUtil {

    public static DataSource createAndConfigureDataSource(UserTenant userTenant, String ip, String port) {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername(userTenant.getUserName());
        ds.setPassword(userTenant.getPassword());
        ds.setJdbcUrl("jdbc:postgresql://"+ip+":"+port+"/sell_motors_leads?sslmode=disable&options=-c%20timezone=America/Sao_Paulo");
        ds.setDriverClassName(userTenant.getDriverClass());
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(0);
        ds.setIdleTimeout(3000);
        ds.setConnectionTimeout(10000);
        ds.setMaxLifetime(60000);
        String tenantConnectionPoolName = userTenant.getDbName() + "-connection-pool";
        ds.setPoolName(tenantConnectionPoolName);
        return ds;
    }

}
