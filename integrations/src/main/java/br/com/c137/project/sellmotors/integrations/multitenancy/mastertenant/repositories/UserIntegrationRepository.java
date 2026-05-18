package br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.repositories;

import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.enums.PlataformNames;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.UserIntegration;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface UserIntegrationRepository extends JpaRepository<UserIntegration, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE UserIntegration ui " +
            "SET ui.accessToken = :accessToken, " +
            "ui.refreshToken = :refreshToken, " +
            "ui.expiresAt = :expiresAt " +
            "WHERE ui.refreshToken = :oldTgToken")
    void updateTokensAndExpiresAt(String accessToken, String refreshToken, LocalDateTime expiresAt, String oldTgToken);

    Optional<UserIntegration> findByTenantIdAndPlatformName(UUID tenantId, PlataformNames platformName);
}
