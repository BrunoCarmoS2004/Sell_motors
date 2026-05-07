package br.com.c137.project.sellmotors.leadcommand.services;

import br.com.c137.project.sellmotors.leadcommand.dtos.LeadDto;

public interface LeadService {
    LeadDto create(LeadDto dto);
    LeadDto update(LeadDto dto);
    void deleteById(String id);
    LeadDto getById(String id);
}
