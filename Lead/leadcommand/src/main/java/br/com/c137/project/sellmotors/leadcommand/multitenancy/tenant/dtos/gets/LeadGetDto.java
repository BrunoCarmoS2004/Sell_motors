package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.gets;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.enums.EntityStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record LeadGetDto(
        UUID id,

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
