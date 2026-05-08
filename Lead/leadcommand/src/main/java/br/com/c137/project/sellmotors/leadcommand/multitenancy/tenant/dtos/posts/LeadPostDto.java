package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.posts;

import java.util.UUID;

public record LeadPostDto(
        String fullName,

        String email,

        String phone,

        String document
) {

}
