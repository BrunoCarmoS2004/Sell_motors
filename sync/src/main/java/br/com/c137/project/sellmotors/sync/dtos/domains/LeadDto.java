package br.com.c137.project.sellmotors.sync.dtos.domains;

import br.com.c137.project.sellmotors.sync.enums.EntityStatus;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "leadDto")
@CompoundIndexes({
        @CompoundIndex(name = "idx_createdBy", def = "{'createdBy': 1}")
})
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

                UUID createdBy) {
}
