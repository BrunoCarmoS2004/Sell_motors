package br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.repositories;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets.UserTokenGetDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.CreationStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.DatabaseStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<UserTokenGetDTO> findByEmailOrInscription(String email, String inscription);

    <T> Optional<T> findById(UUID uuid, Class<T> type);

    <T> Page<T> findBy(Pageable pageable, Class<T> type);

    boolean existsByInscription(String inscription);

    boolean existsByEmail(String inscription);

    boolean existsByInscriptionAndIdNot(String inscription, UUID id);

    boolean existsByEmailAndIdNot(String inscription, UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.creationStatus = :creationStatus WHERE u.id = :id")
    void updateCreationStatus(CreationStatus creationStatus, UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.entityStatus = :entityStatus WHERE u.id = :id")
    void updateEntityStatus(EntityStatus entityStatus, UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.lastAcess = :lastAcess WHERE u.id = :id")
    void updateLastAcess(LocalDateTime lastAcess, UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.dbStatus = :databaseStatus WHERE u.id = :id")
    void updateDataBaseStuatus(DatabaseStatus databaseStatus, UUID id);
}
