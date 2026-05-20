package br.com.c137.project.sellmotors.sync.services;

import br.com.c137.project.sellmotors.sync.dtos.domains.LeadDto;

public interface LeadService {

    void saveLead(LeadDto dto);
}
