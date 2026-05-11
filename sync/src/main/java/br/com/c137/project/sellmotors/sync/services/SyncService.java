package br.com.c137.project.sellmotors.sync.services;

import br.com.c137.project.sellmotors.sync.dtos.LeadDto;
import br.com.c137.project.sellmotors.sync.dtos.VehicleDto;

public interface SyncService {

    void syncLead(LeadDto dto);

    void syncVehicle(VehicleDto dto);
}
