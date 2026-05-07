package br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets;


import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.CreationStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.UserRoles;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserGetDTO(
        UUID id,

        String name,

        String inscription,

        String email,

        String password,

        String telephone,

        String cellPhone,

        LocalDateTime lastAcess,

        UserRoles userRole,

        CreationStatus creationStatus,

        EntityStatus entityStatus
) {
}
