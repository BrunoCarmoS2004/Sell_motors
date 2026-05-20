package br.com.c137.project.sellmotors.sync.services;

import br.com.c137.project.sellmotors.sync.dtos.domains.LeadDto;
import br.com.c137.project.sellmotors.sync.dtos.domains.VehicleDto;

public interface SyncService {

    void syncLead(LeadDto dto);

    void syncVehicle(VehicleDto dto);
}
