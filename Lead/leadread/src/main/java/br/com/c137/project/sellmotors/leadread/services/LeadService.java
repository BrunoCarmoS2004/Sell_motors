package br.com.c137.project.sellmotors.leadread.services;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;

import java.util.List;
import java.util.UUID;

public interface LeadService {

    List<LeadDomain> findAllByTenantId(UUID tenantId);

    List<LeadDomain> listByNameLikeIgnoreCase(String name);

    List<LeadDomain> listByEmailLikeIgnoreCase(String email);
}
