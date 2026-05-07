package br.com.c137.project.sellmotors.leadcommand.services.impl;

import br.com.c137.project.sellmotors.leadcommand.dtos.LeadDto;
import br.com.c137.project.sellmotors.leadcommand.entities.LeadEntity;
import br.com.c137.project.sellmotors.leadcommand.repositories.LeadRepository;
import br.com.c137.project.sellmotors.leadcommand.services.LeadService;
import br.com.c137.project.sellmotors.leadcommand.utils.MapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;
    private final MapperUtil<LeadEntity, LeadDto> mapperUtil;

    public LeadServiceImpl(LeadRepository leadRepository, MapperUtil mapperUtil, ModelMapper modelMapper) {
        this.leadRepository = leadRepository;
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
        LeadEntity leadEntity = leadRepository.findById(dto.getId()).orElseThrow(
                () -> new RuntimeException("Lead not found")
        );
        mapperUtil.updateSource(dto, leadEntity);
        leadRepository.save(leadEntity);

        return mapperUtil.convertToTarget(leadEntity);
    }

    @Override
    public void deleteById(String id) {
        LeadEntity leadEntity = leadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lead not found")
        );

        leadRepository.delete(leadEntity);

    }

    @Override
    public LeadDto getById(String id) {
        LeadEntity leadEntity = leadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lead not found")
        );

        return mapperUtil.convertToTarget(leadEntity);
    }
}
