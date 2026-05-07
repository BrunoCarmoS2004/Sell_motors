package br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets;


import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.UserRoles;

import java.util.UUID;

public record UserTokenGetDTO(
        UUID id,

        String password,

        UserRoles userRole
) {
}
