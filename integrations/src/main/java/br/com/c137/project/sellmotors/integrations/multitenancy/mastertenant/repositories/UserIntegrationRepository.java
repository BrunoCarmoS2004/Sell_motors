package br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.repositories;

import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.UserIntegration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserIntegrationRepository extends JpaRepository<UserIntegration, UUID> {
}
