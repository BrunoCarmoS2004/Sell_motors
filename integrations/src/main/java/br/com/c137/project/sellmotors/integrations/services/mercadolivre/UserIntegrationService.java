package br.com.c137.project.sellmotors.integrations.services.mercadolivre;

import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets.MlTokenResponse;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets.VehicleGetDTO;

import java.util.UUID;

public interface UserIntegrationService {

    void getTokenMercadoLivre(String code);
    void getRefreshTokenMercadoLivre(UUID tenantId);
    MlTokenResponse getAccessTokenOrRefreshTokenMercadoLivre(String code, String grantType);

    void postVehicleMercadoLivre(VehicleGetDTO dto);
}
