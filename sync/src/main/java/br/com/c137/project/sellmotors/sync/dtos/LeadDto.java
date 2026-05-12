package br.com.c137.project.sellmotors.sync.dtos;

import br.com.c137.project.sellmotors.sync.enums.EntityStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record LeadDto(
        UUID id,

        String fullName,

        String email,

        String phone,

        String document,

        EntityStatus entityStatus,

        LocalDateTime deletedAt,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        UUID createdBy
) {
}
