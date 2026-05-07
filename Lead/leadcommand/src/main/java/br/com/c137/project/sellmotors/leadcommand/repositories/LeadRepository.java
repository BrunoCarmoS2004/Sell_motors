package br.com.c137.project.sellmotors.leadcommand.repositories;

import br.com.c137.project.sellmotors.leadcommand.entities.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, String> {
}
