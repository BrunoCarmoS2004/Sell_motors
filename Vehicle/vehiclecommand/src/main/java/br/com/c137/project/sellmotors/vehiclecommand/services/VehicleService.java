package br.com.c137.project.sellmotors.vehiclecommand.services;

import br.com.c137.project.sellmotors.vehiclecommand.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.vehiclecommand.mappers.VehicleMapper;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.gets.VehicleGetDTO;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.posts.VehiclePostDTO;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.puts.VehiclePutDTO;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.Vehicle;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.repositories.VehicleRepository;
import br.com.c137.project.sellmotors.vehiclecommand.validations.VehicleValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    private final VehicleValidation vehicleValidation;

    private final VehicleMapper vehicleMapper;

    private final MessageUtils messageUtils;

    public VehicleService(VehicleRepository vehicleRepository, VehicleValidation vehicleValidation, VehicleMapper vehicleMapper, MessageUtils messageUtils) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleValidation = vehicleValidation;
        this.vehicleMapper = vehicleMapper;
        this.messageUtils = messageUtils;
    }

    public PagedModel<VehicleGetDTO> getAll(Pageable pageable) {
        Page<VehicleGetDTO> vehiclePage = vehicleRepository.getAll(pageable, VehicleGetDTO.class);
        return new PagedModel<>(vehiclePage);
    }

    public VehicleGetDTO getVehicleById(UUID id) {
        return vehicleRepository.getById(id, VehicleGetDTO.class).orElseThrow(() -> new NotFoundException(getNotFoundMessage()));
    }

    public VehicleGetDTO postVehicle(VehiclePostDTO vehiclePostDTO) {
        vehicleValidation.placaExistsValidation(vehiclePostDTO.placa());
        vehicleValidation.chassiExistisValidation(vehiclePostDTO.chassi());
        Vehicle vehicle = vehicleMapper.postToVehicle(vehiclePostDTO);
        return saveAndReturn(vehicle);
    }

    public VehicleGetDTO putVehicle(UUID id, VehiclePutDTO vehiclePutDTO) {
        vehicleValidation.placaExistsValidation(vehiclePutDTO.placa());
        vehicleValidation.chassiExistisValidation(vehiclePutDTO.chassi());
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new NotFoundException(getNotFoundMessage()));
        vehicle = vehicleMapper.putToVehicle(vehiclePutDTO, vehicle);
        return saveAndReturn(vehicle);
    }

    public void deleteVehicle(UUID id) {
        vehicleValidation.vehicleExistsValidation(id);
        updateEntityStatus(EntityStatus.DELETADO, id);
    }

    public void inativarVehicle(UUID id) {
        vehicleValidation.vehicleExistsValidation(id);
        updateEntityStatus(EntityStatus.INATIVO, id);
    }

    private void updateEntityStatus(EntityStatus entityStatus, UUID id) {
        vehicleRepository.updateEntityStatus(entityStatus, id);
    }

    private String getNotFoundMessage() {
        return messageUtils.getMessage("vehicle.not-found");
    }

    private VehicleGetDTO saveAndReturn(Vehicle vehicle) {
        return vehicleMapper.vehicleToVehicleGetDTO(vehicleRepository.save(vehicle));
    }
}
