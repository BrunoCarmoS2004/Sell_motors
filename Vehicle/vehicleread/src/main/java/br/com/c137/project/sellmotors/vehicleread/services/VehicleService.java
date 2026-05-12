package br.com.c137.project.sellmotors.vehicleread.services;

import br.com.c137.project.sellmotors.vehicleread.dtos.VehicleFullDTO;
import br.com.c137.project.sellmotors.vehicleread.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.vehicleread.exceptions.TokenValidationException;
import br.com.c137.project.sellmotors.vehicleread.repositories.VehicleRepository;
import br.com.c137.project.sellmotors.vehicleread.utils.MessageUtils;
import br.com.c137.project.sellmotors.vehicleread.utils.ServiceUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    private final MessageUtils messageUtils;

    public VehicleService(VehicleRepository vehicleRepository, MessageUtils messageUtils) {
        this.vehicleRepository = vehicleRepository;
        this.messageUtils = messageUtils;
    }

    public PagedModel<VehicleFullDTO> findAllByCreatedBy(Pageable pageable) {
        try {
            UUID usuarioId = getUserIdFromToken();
            return new PagedModel<>(vehicleRepository.findAllByCreatedBy(usuarioId, pageable, VehicleFullDTO.class));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public VehicleFullDTO findVehicleFullById(UUID id) {
        try{
            UUID usuarioId = getUserIdFromToken();
            return vehicleRepository.findById(id, usuarioId, VehicleFullDTO.class).orElseThrow(
                    () -> new NotFoundException(messageUtils.getMessage("vehicle.not-found"))
            );
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public PagedModel<VehicleFullDTO> findAllByModeloLikeIgnoreCase(String modelo, Pageable pageable) {
        try {
            UUID usuarioId = getUserIdFromToken();
            return new PagedModel<>(vehicleRepository.findByModeloLikeIgnoreCase(modelo, usuarioId, pageable, VehicleFullDTO.class));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public PagedModel<VehicleFullDTO> findAllByMarcaLikeIgnoreCase(String marca, Pageable pageable) {
        try {
            UUID usuarioId = getUserIdFromToken();
            return new PagedModel<>(vehicleRepository.findByMarcaLikeIgnoreCase(marca, usuarioId, pageable, VehicleFullDTO.class));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private UUID getUserIdFromToken(){
        UUID uuid = ServiceUtils.getUserIdFromToken();
        if (uuid != null){
            return uuid;
        }
        throw new TokenValidationException(messageUtils.getMessage("token.error"));
    }
}
