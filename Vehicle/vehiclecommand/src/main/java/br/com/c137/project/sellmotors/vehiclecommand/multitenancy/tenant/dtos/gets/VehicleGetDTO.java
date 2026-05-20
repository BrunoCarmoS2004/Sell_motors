package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.gets;

import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.VehicleStatus;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.AnuncioAttribute;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.Location;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.Picture;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
