package br.com.c137.project.sellmotors.integrations.services.mercadolivre.impl;

import br.com.c137.project.sellmotors.integrations.exceptions.MercadoLivreException;
import br.com.c137.project.sellmotors.integrations.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets.VehicleGetDTO;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets.requests.MercadoLivreVehiclePayload;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.enums.PlataformNames;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.*;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.repositories.UserIntegrationRepository;
import br.com.c137.project.sellmotors.integrations.services.mercadolivre.TokenMercadoLivreService;
import br.com.c137.project.sellmotors.integrations.services.mercadolivre.UserIntegrationService;
import br.com.c137.project.sellmotors.integrations.utils.MessageUtils;
import br.com.c137.project.sellmotors.integrations.utils.SyncLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.com.c137.project.sellmotors.integrations.utils.ServiceUtils.URL_BASE_MERCADO_LIVRE_ITENS;

@Service
public class UserIntegrationServiceImpl implements UserIntegrationService {

    private static final ObjectMapper MERCADO_LIVRE_OBJECT_MAPPER = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    private final RestTemplate restTemplate = new RestTemplate();

    private final UserIntegrationRepository userIntegrationRepository;

    private final TokenMercadoLivreService tokenMercadoLivreService;

    private final MessageUtils messageUtils;

    public UserIntegrationServiceImpl(UserIntegrationRepository userIntegrationRepository, TokenMercadoLivreService tokenMercadoLivreService, MessageUtils messageUtils) {
        this.userIntegrationRepository = userIntegrationRepository;
        this.tokenMercadoLivreService = tokenMercadoLivreService;
        this.messageUtils = messageUtils;
    }

    @Override
    public void postVehicleMercadoLivre(VehicleGetDTO dto) {
        UUID tenantId = dto.createdBy();

        UserIntegration integration = userIntegrationRepository
                .findByTenantIdAndPlatformName(tenantId, PlataformNames.MERCADO_LIVRE)
                .orElseThrow(() -> new NotFoundException(messageUtils.getMessage("user.integration.not-found")));

        integration = verifyToken(integration, tenantId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(integration.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        MercadoLivreVehiclePayload payload = createPayload(dto);

        try {
            String payloadJson = MERCADO_LIVRE_OBJECT_MAPPER.writeValueAsString(payload);
            HttpEntity<String> request = new HttpEntity<>(payloadJson, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    URL_BASE_MERCADO_LIVRE_ITENS,
                    request,
                    String.class
            );

            SyncLogger.info("Anúncio criado no Mercado Livre: " + response.getBody());
        } catch (JsonProcessingException e) {
            SyncLogger.error("Erro ao serializar payload para o Mercado Livre: " + e.getMessage());
            throw new MercadoLivreException("Erro ao serializar payload para o Mercado Livre");
        } catch (Exception e) {
            SyncLogger.error("Erro ao publicar no Mercado Livre: " + e.getMessage());
            throw new MercadoLivreException("Erro ao publicar no Mercado Livre");
        }

    }

    private @NonNull UserIntegration verifyToken(UserIntegration integration, UUID tenantId) {
        if (integration.getExpiresAt() != null && integration.getExpiresAt().isBefore(LocalDateTime.now())) {
            tokenMercadoLivreService.getRefreshTokenMercadoLivre(tenantId);

            integration = userIntegrationRepository
                    .findByTenantIdAndPlatformName(tenantId, PlataformNames.MERCADO_LIVRE)
                    .orElseThrow(() -> new NotFoundException(messageUtils.getMessage("user.integration.not-found")));
        }
        return integration;
    }

    private MercadoLivreVehiclePayload createPayload(VehicleGetDTO dto) {
        List<Picture> pictures = createPictures(dto);

        Location location = createLocation(dto);

        List<AnuncioAttribute> attributes = createAttributes(dto);

        return createPayload(dto, pictures, location, attributes);
    }

    private static @NonNull MercadoLivreVehiclePayload createPayload(VehicleGetDTO dto, List<Picture> pictures, Location location, List<AnuncioAttribute> attributes) {
        return new MercadoLivreVehiclePayload(
                dto.title(),
                dto.description(),
                List.of("marketplace"),
                dto.videoId(),
                "MLB1744",
                dto.price(),
                "BRL",
                dto.listingTypeId(),
                1,
                pictures,
                location,
                attributes
        );
    }

    private static @NonNull List<AnuncioAttribute> createAttributes(VehicleGetDTO dto) {
        return dto.attributes().stream()
                .map(attribute -> new AnuncioAttribute(
                        attribute.id(),
                        attribute.valueName()
                ))
                .toList();
    }

    private static @NonNull Location createLocation(VehicleGetDTO dto) {
        return new Location(
                dto.locations().addressLine(),
                dto.locations().zipCode(),
                new City(dto.locations().city().id())
        );
    }

    private static @NonNull List<Picture> createPictures(VehicleGetDTO dto) {
        return dto.pictures().stream()
                .map(picture -> new Picture(picture.source()))
                .toList();
    }
}
