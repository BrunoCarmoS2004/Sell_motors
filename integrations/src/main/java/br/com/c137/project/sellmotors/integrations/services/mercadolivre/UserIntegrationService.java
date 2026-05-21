package br.com.c137.project.sellmotors.integrations.services.mercadolivre;

import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets.VehicleGetDTO;

public interface UserIntegrationService {
    void postVehicleMercadoLivre(VehicleGetDTO dto);
}
