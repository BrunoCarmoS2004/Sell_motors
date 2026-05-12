package br.com.c137.project.sellmotors.leadread.repositories;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface LeadRepository extends MongoRepository<LeadDomain, UUID> {
    List<LeadDomain> findAllByTenantId(UUID tenantId);
}
