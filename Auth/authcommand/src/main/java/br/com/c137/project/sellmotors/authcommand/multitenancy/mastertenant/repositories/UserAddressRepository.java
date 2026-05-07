package br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.repositories;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models.UserAddress;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserAddressRepository extends JpaRepository<UserAddress, UUID> {
    <T> Page<T> findBy(Pageable pageable, Class<T> type);

    <T> Optional<T> findById(UUID id, Class<T> type);

    Page<UserAddress> findAllByAddressOf(UUID addressOf, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE UserAddress ua SET ua.entityStatus = :entityStatus WHERE ua.id = :id")
    void updateEntityStatus(EntityStatus entityStatus, UUID id);

    boolean existsByZipCodeAndNumber(String zipCode, Integer number);

    boolean existsByZipCodeAndNumberAndIdNot(String zipCode, Integer number, UUID id);
}
