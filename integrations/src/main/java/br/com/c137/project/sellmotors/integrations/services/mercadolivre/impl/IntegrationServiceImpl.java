package br.com.c137.project.sellmotors.integrations.services.mercadolivre.impl;

import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets.VehicleGetDTO;
import br.com.c137.project.sellmotors.integrations.services.mercadolivre.IntegrationService;
import br.com.c137.project.sellmotors.integrations.services.mercadolivre.UserIntegrationService;
import org.springframework.stereotype.Service;

@Service
public class IntegrationServiceImpl implements IntegrationService {

    private final UserIntegrationService userIntegrationService;

    public IntegrationServiceImpl(UserIntegrationService userIntegrationService) {
        this.userIntegrationService = userIntegrationService;
    }

    @Override
    public void integration(VehicleGetDTO dto) {
        if(dto.channels().contains("mercadolivre")) {
            userIntegrationService.postVehicleMercadoLivre(dto);
        }
    }
}
