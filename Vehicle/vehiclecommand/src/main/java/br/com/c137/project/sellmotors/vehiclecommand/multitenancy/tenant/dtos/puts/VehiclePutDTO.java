package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.puts;

import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.VehicleStatus;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.AnuncioAttribute;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.Location;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.Picture;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record VehiclePutDTO(
        @NotBlank(message = "O título é obrigatório")
        @Size(min = 5, max = 100, message = "O título deve ter entre 5 e 100 caracteres")
        String title,

        @NotBlank(message = "A descrição é obrigatória")
        String description,

        String videoId,

        @NotBlank(message = "A categoria é obrigatória")
        String categoryId,

        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        BigDecimal price,

        @NotBlank(message = "A moeda (currency_id) é obrigatória")
        @Size(min = 3, max = 3, message = "A moeda deve ter exatamente 3 caracteres (Ex: BRL)")
        String currencyId,

        @NotBlank(message = "O tipo de anúncio (listing_type_id) é obrigatório")
        String listingTypeId,

        @NotNull(message = "A quantidade disponível é obrigatória")
        @Min(value = 1, message = "A quantidade mínima deve ser pelo menos 1")
        Integer availableQuantity,

        @NotNull(message = "O status do veículo é obrigatório")
        VehicleStatus statusVeiculo,

        @NotNull(message = "O channel do veículo é obrigatório")
        List<String> channels,

        @NotNull(message = "Os atributos do veículo é obrigatório")
        List<AnuncioAttribute> attributes,

        @NotNull(message = "A localidade do veículo é obrigatório")
        Location locations,

        @NotNull(message = "As fotos do veículo é obrigatório")
        List<Picture> pictures
) {
}
