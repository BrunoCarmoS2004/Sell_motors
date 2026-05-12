package br.com.c137.project.sellmotors.leadread.repositories;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LeadRepository extends MongoRepository<LeadDomain, UUID> {

    @Query("{ 'createdBy' : ?0 }")
    <T> Page<T> findAllByCreatedBy(UUID createdBy, Pageable pageable, Class<T> type);

    @Query("{'fullName' :  {$regex: ?0, $options:  'i'}, 'createdBy' :  ?1}")
    <T> Page<T> findByNameLikeIgnoreCase(String name, UUID createdBy, Pageable pageable, Class<T> type);

    @Query("{'email' :  {$regex: ?0, $options:  'i'}, 'createdBy' :  ?1}")
    <T> Page<T> findByEmailLikeIgnoreCase(String name, UUID createdBy, Pageable pageable, Class<T> type);

    @Query("{'id' :  ?0, 'createdBy' :  ?1}")
    <T> Optional<T> findById(UUID id, UUID createdBy, Class<T> type);
}
