package br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets;


import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.NeighborhoodType;

import java.util.UUID;

public record UserAddressGetDTO(
        UUID id,

        String zipCode,

        String streetAddress,

        Integer number,

        NeighborhoodType neighborhoodType,

        String neighborhood,

        String complement,

        String state,

        String city,

        String cityIbge,

        UUID addressOf,

        EntityStatus entityStatus
) {}


