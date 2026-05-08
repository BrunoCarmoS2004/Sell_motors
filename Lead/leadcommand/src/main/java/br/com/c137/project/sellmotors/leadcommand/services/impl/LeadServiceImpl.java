package br.com.c137.project.sellmotors.leadcommand.services.impl;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.LeadDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.models.LeadEntity;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.repositories.LeadRepository;
import br.com.c137.project.sellmotors.leadcommand.services.LeadService;
import br.com.c137.project.sellmotors.leadcommand.utils.MapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final MapperUtil<LeadEntity, LeadDto> mapperUtil;

    public LeadServiceImpl() {
        this.mapperUtil = new MapperUtil<>(modelMapper, LeadEntity.class, LeadDto.class);
    }

    @Override
    public LeadDto create(LeadDto dto) {
        LeadEntity leadEntity = mapperUtil.convertToSource(dto);
        leadRepository.save(leadEntity);
        return mapperUtil.convertToTarget(leadEntity);
    }

    @Override
    public LeadDto update(LeadDto dto) {
        LeadEntity leadEntity = leadRepository.findById(dto.id()).orElseThrow(
                () -> new RuntimeException("Lead not found")
        );
        mapperUtil.updateSource(dto, leadEntity);
        leadRepository.save(leadEntity);

        return mapperUtil.convertToTarget(leadEntity);
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

        return mapperUtil.convertToTarget(leadEntity);
    }
}
