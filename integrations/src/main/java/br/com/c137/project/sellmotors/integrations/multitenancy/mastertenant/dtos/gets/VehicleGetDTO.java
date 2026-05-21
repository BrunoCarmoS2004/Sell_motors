package br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets;

import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.enums.VehicleStatus;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.AnuncioAttribute;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.Location;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.Picture;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record VehicleGetDTO(
        UUID id,

        String title,

        String description,

        String videoId,

        BigDecimal price,

        String listingTypeId,

        VehicleStatus statusVeiculo,

        List<String> channels,

        List<AnuncioAttribute> attributes,

        Location locations,

        List<Picture> pictures,

        LocalDateTime deletedAt,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        EntityStatus entityStatus,

        UUID createdBy
) {
}
