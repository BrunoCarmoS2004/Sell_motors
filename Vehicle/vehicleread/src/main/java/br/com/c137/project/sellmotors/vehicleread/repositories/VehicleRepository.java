package br.com.c137.project.sellmotors.vehicleread.repositories;

import br.com.c137.project.sellmotors.vehicleread.domains.vehicle.VehicleDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends MongoRepository<VehicleDomain, UUID> {

    @Query(value = "{'id': ?0, 'createdBy' : ?1}")
    <T> Optional<T> findById(UUID id, UUID createdBy, Class<T> type);

    @Query("{ 'createdBy' : ?0 }")
    <T> Page<T> findAllByCreatedBy(UUID createdBy, Pageable pageable, Class<T> type);

    @Query("{'modelo' :  {$regex: ?0, $options:  'i'}, 'createdBy' :  ?1}")
    <T> Page<T> findByModeloLikeIgnoreCase(String modelo, UUID createdBy, Pageable pageable, Class<T> type);

    @Query("{'marca' :  {$regex: ?0, $options:  'i'}, 'createdBy' :  ?1}")
    <T> Page<T> findByMarcaLikeIgnoreCase(String marca, UUID createdBy, Pageable pageable, Class<T> type);
}
