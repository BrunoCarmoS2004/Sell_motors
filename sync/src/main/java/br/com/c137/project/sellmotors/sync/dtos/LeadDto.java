package br.com.c137.project.sellmotors.sync.dtos;

import java.util.UUID;

public record LeadDto(
        UUID id,

        String fullName,

        String email,

        String phone,

        String document
) {
}
