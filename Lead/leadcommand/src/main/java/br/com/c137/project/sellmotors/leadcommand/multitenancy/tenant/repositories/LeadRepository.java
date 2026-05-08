package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.repositories;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.models.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, UUID> {
}
