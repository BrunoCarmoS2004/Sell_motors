package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.gets;

import java.util.UUID;

public record LeadGetDto(
        UUID id,

        String fullName,

        String email,

        String phone,

        String document
) {

}
