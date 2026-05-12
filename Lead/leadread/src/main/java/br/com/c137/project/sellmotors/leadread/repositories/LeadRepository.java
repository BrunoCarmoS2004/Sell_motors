package br.com.c137.project.sellmotors.leadread.repositories;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LeadRepository extends MongoRepository<LeadDomain, UUID> {

    @Query("{ 'createdBy' : ?0 }")
    Page<LeadDomain> findAllByCreatedBy(UUID createdBy, Pageable pageable);

    @Query("{'fullName' :  {$regex: ?0, $options:  'i'}, 'createdBy' :  ?1}")
    Page<LeadDomain> findByNameLikeIgnoreCase(String name, UUID createdBy, Pageable pageable);

    @Query("{'email' :  {$regex: ?0, $options:  'i'}, 'createdBy' :  ?1}")
    Page<LeadDomain> findByEmailLikeIgnoreCase(String name, UUID createdBy, Pageable pageable);
}
