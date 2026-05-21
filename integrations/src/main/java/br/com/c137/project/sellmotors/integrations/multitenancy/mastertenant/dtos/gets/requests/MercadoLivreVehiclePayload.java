package br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets.requests;

import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.AnuncioAttribute;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.Location;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.Picture;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record MercadoLivreVehiclePayload(
        String title,
        String description,
        List<String> channels,
        String videoId,
        String categoryId,
        BigDecimal price,
        String currencyId,
        String listingTypeId,
        Integer availableQuantity,
        List<Picture> pictures,
        Location location,
        List<AnuncioAttribute> attributes
) {
}
