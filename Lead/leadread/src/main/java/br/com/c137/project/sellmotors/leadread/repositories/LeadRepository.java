package br.com.c137.project.sellmotors.leadread.repositories;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LeadRepository extends MongoRepository<LeadDomain, UUID> {

    List<LeadDomain> findAllByCreatedBy(String createdBy);
}
