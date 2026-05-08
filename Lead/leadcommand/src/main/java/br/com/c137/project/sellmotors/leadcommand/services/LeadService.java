package br.com.c137.project.sellmotors.leadcommand.services;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.LeadDto;

import java.util.UUID;

public interface LeadService {
    LeadDto create(LeadDto dto);
    LeadDto update(LeadDto dto);
    void deleteById(UUID id);
    LeadDto getById(UUID id);
}
