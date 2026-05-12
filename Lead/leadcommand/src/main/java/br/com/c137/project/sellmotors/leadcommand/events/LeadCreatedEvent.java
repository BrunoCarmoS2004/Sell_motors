package br.com.c137.project.sellmotors.leadcommand.events;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.enums.EntityStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record LeadCreatedEvent(
        UUID id,
        UUID tenantId,
        String fullName,
        String email,
        String phone,
        String document,
        EntityStatus entityStatus,
        LocalDateTime deletedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
