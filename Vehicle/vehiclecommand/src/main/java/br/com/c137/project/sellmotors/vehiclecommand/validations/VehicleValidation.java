package br.com.c137.project.sellmotors.vehiclecommand.validations;

import br.com.c137.project.sellmotors.vehiclecommand.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.repositories.VehicleRepository;
import br.com.c137.project.sellmotors.vehiclecommand.utils.MessageUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VehicleValidation {

    private final VehicleRepository vehicleRepository;

    private final MessageUtils messageUtils;

    public VehicleValidation(VehicleRepository vehicleRepository, MessageUtils messageUtils) {
        this.vehicleRepository = vehicleRepository;
        this.messageUtils = messageUtils;
    }

    public void vehicleExistsValidation(UUID id) {
        if (!vehicleRepository.existsById(id)) {
            throw new NotFoundException(messageUtils.getMessage("vehicle.not-exists"));
        }
    }

    private String getPlacaValidationMessage(){
        return messageUtils.getMessage("vehicle.placa.already-exists");
    }
}
