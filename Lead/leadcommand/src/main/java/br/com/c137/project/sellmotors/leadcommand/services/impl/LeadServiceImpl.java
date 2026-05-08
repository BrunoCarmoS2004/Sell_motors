package br.com.c137.project.sellmotors.leadcommand.services.impl;

import br.com.c137.project.sellmotors.leadcommand.mappers.LeadMapper;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.LeadDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.models.LeadEntity;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.repositories.LeadRepository;
import br.com.c137.project.sellmotors.leadcommand.services.LeadService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;

    private final LeadMapper leadMapper;


    public LeadServiceImpl(LeadRepository leadRepository, LeadMapper leadMapper) {
        this.leadRepository = leadRepository;
        this.leadMapper = leadMapper;
    }

    @Override
    public LeadDto create(LeadDto dto) {
        LeadEntity leadEntity = leadMapper.leadDtoToEntity(dto);
        leadRepository.save(leadEntity);
        return leadMapper.leadEntityToDto(leadEntity);
    }

    @Override
    public LeadDto update(LeadDto dto) {
        LeadEntity leadEntity = leadRepository.findById(dto.id()).orElseThrow(
                () -> new RuntimeException("Lead not found")
        );
        leadMapper.leadUpdate(dto, leadEntity);
        leadRepository.save(leadEntity);

        return leadMapper.leadEntityToDto(leadEntity);
    }

    @Override
    public void deleteById(UUID id) {
        LeadEntity leadEntity = leadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lead not found")
        );

        leadRepository.delete(leadEntity);

    }

    @Override
    public LeadDto getById(UUID id) {
        LeadEntity leadEntity = leadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lead not found")
        );

        return leadMapper.leadEntityToDto(leadEntity);
    }
}
