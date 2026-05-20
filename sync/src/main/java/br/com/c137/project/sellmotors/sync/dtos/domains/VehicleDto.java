package br.com.c137.project.sellmotors.sync.dtos.domains;

import br.com.c137.project.sellmotors.sync.dtos.auxiliary.vehicle.AnuncioAttribute;
import br.com.c137.project.sellmotors.sync.dtos.auxiliary.vehicle.Location;
import br.com.c137.project.sellmotors.sync.dtos.auxiliary.vehicle.Picture;
import br.com.c137.project.sellmotors.sync.enums.EntityStatus;
import br.com.c137.project.sellmotors.sync.enums.VehicleStatus;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Document(collection = "vehicleDto")
@CompoundIndexes({
        @CompoundIndex(name = "idx_createdBy", def = "{'createdBy': 1}")
})
public record VehicleDto(
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
