package br.com.c137.project.sellmotors.sync.services.impl;

import br.com.c137.project.sellmotors.sync.dtos.LeadDto;
import br.com.c137.project.sellmotors.sync.dtos.VehicleDto;
import br.com.c137.project.sellmotors.sync.services.LeadService;
import br.com.c137.project.sellmotors.sync.services.SyncService;
import br.com.c137.project.sellmotors.sync.services.VehicleService;
import br.com.c137.project.sellmotors.sync.utils.MessageUtils;
import br.com.c137.project.sellmotors.sync.utils.SyncLogger;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SyncServiceImpl implements SyncService {

    private final LeadService leadService;

    private final VehicleService vehicleService;

    private final MessageUtils messageUtils;

    public SyncServiceImpl(LeadService leadService, VehicleService vehicleService, MessageUtils messageUtils) {
        this.leadService = leadService;
        this.vehicleService = vehicleService;
        this.messageUtils = messageUtils;
    }

    @Override
    public void syncLead(LeadDto dto) {
        try {
            SyncLogger.info(messageUtils.getMessage("sync.saving-lead", dto.id()));
            leadService.saveLead(dto);
        } catch (Exception e) {
            SyncLogger.error(messageUtils.getMessage("sync.saving-lead-error", e.getMessage()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncVehicle(VehicleDto dto) {
        try {
            SyncLogger.info(messageUtils.getMessage("sync.saving-vehicle", dto.id()));
            vehicleService.saveVehicle(dto);
        } catch (Exception e) {
            SyncLogger.error(messageUtils.getMessage("sync.saving-vehicle-error", e.getMessage()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
