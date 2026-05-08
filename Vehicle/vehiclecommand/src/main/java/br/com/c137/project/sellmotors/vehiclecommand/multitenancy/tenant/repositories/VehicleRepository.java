package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.repositories;

import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    <T> Optional<T> getById(UUID id, Class<T> type);
    <T> Page<T> getAll(Pageable pageable, Class<T> type);

    boolean existsByPlaca(String placa);

    boolean existsByChassi(String chassi);

    boolean existsByPlacaAndIdNot(String placa, UUID id);

    boolean existsByChassiAndIdNot(String chassi, UUID id);

    @Transactional
    @Modifying
    @Query("")
    void updateEntityStatus(EntityStatus entityStatus, UUID id);
}
