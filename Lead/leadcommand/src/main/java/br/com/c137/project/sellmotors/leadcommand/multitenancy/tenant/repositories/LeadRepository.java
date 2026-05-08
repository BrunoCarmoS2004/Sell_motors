package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.repositories;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.gets.LeadGetDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.models.LeadEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, UUID> {
    @Transactional
    @Modifying
    @Query("UPDATE LeadEntity l SET l.entityStatus = :entityStatus WHERE l.id = :id")
    void updateEntityStatus(UUID id, EntityStatus entityStatus);

    <T> Page<T> findAllBy(Pageable pageable, Class<T> type);
}
