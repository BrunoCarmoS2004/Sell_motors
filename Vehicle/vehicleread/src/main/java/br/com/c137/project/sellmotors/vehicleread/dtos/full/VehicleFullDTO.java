package br.com.c137.project.sellmotors.vehicleread.dtos.full;

import br.com.c137.project.sellmotors.vehicleread.dtos.auxiliary.AnuncioAttribute;
import br.com.c137.project.sellmotors.vehicleread.dtos.auxiliary.Location;
import br.com.c137.project.sellmotors.vehicleread.dtos.auxiliary.Picture;
import br.com.c137.project.sellmotors.vehicleread.enums.EntityStatus;
import br.com.c137.project.sellmotors.vehicleread.enums.VehicleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record VehicleFullDTO(
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
