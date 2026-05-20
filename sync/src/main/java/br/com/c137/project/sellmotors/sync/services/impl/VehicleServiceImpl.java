package br.com.c137.project.sellmotors.sync.services.impl;

import br.com.c137.project.sellmotors.sync.dtos.domains.VehicleDto;
import br.com.c137.project.sellmotors.sync.repositores.VehicleRepository;
import br.com.c137.project.sellmotors.sync.services.VehicleService;
import br.com.c137.project.sellmotors.sync.utils.MessageUtils;
import br.com.c137.project.sellmotors.sync.utils.SyncLogger;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final MessageUtils messageUtils;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, MessageUtils messageUtils) {
        this.vehicleRepository = vehicleRepository;
        this.messageUtils = messageUtils;
    }

    @Override
    public void saveVehicle(VehicleDto dto) {

        try {
            SyncLogger.info(messageUtils.getMessage("sync.saving-vehicle", dto.id()));
            vehicleRepository.save(dto);
        } catch (Exception e) {
            SyncLogger.error(messageUtils.getMessage("sync.saving-vehicle-error", e.getMessage()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }

    }
}
