package br.com.c137.project.sellmotors.leadread.dtos.gets;

import br.com.c137.project.sellmotors.leadread.enums.EntityStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record LeadFullDto( UUID id,

         String fullName,

         String email,

         String phone,

         String document,

         EntityStatus entityStatus,

         LocalDateTime deletedAt,

         LocalDateTime createdAt,

         LocalDateTime updatedAt,

         UUID createdBy) {
}
