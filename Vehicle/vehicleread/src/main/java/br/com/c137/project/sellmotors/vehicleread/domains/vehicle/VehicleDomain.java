package br.com.c137.project.sellmotors.vehicleread.domains.vehicle;

import br.com.c137.project.sellmotors.vehicleread.dtos.auxiliary.AnuncioAttribute;
import br.com.c137.project.sellmotors.vehicleread.dtos.auxiliary.Location;
import br.com.c137.project.sellmotors.vehicleread.dtos.auxiliary.Picture;
import br.com.c137.project.sellmotors.vehicleread.enums.EntityStatus;
import br.com.c137.project.sellmotors.vehicleread.enums.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "vehicleDto")
@TypeAlias("br.com.c137.project.sellmotors.sync.dtos.domains.VehicleDto")
public class VehicleDomain {
    @Id
    UUID id;

    String title;

    String description;

    String videoId;

    BigDecimal price;

    String listingTypeId;

    VehicleStatus statusVeiculo;

    List<String> channels;

    List<AnuncioAttribute> attributes;

    Location locations;

    List<Picture> pictures;

    LocalDateTime deletedAt;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    EntityStatus entityStatus;

    UUID createdBy;
}
