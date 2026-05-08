package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.config;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.mastertenant.config.DBContextHolder;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.util.StringUtils;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {
    //TODO VERIFICAR SE DEVE FICAR LEADS OU MASTER
	private static final String DEFAULT_TENANT_ID = "sell_motors_leads";

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
