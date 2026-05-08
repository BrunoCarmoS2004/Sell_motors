package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.util.StringUtils;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {
    //TODO VERIFICAR SE DEVE FICAR LEADS OU MASTER
	private static final String DEFAULT_TENANT_ID = "sell_motors_vehicles";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenant = DBContextHolder.getCurrentDb();
        return StringUtils.hasText(tenant) ? tenant : DEFAULT_TENANT_ID;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
	
}
