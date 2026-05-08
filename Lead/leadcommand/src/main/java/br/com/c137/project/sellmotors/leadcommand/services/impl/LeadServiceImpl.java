package br.com.c137.project.sellmotors.leadcommand.services.impl;

import br.com.c137.project.sellmotors.leadcommand.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.leadcommand.mappers.LeadMapper;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.gets.LeadGetDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.posts.LeadPostDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.puts.LeadPutDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.models.LeadEntity;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.repositories.LeadRepository;
import br.com.c137.project.sellmotors.leadcommand.services.LeadService;
import br.com.c137.project.sellmotors.leadcommand.utils.MessageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;

    private final LeadMapper leadMapper;

    private final MessageUtils messageUtils;


    public LeadServiceImpl(LeadRepository leadRepository, LeadMapper leadMapper, MessageUtils messageUtils) {
        this.leadRepository = leadRepository;
        this.leadMapper = leadMapper;
        this.messageUtils = messageUtils;
    }



    @Override
    public LeadGetDto create(LeadPostDto dto) {
        LeadEntity leadEntity = leadMapper.leadDtoPostToEntity(dto);
        leadRepository.save(leadEntity);
        return leadMapper.leadEntityToDtoGet(leadEntity);
    }

    @Override
    public LeadGetDto update(LeadPutDto dto, UUID id) {
        LeadEntity leadEntity = leadRepository.findById(id).orElseThrow(
                () -> new RuntimeException(getNotFoundMessage())
        );
        leadMapper.leadUpdatePut(dto, leadEntity);
        leadRepository.save(leadEntity);

        return leadMapper.leadEntityToDtoGet(leadEntity);
    }

    @Override
    public void deleteById(UUID id) {
        if(!leadRepository.existsById(id)) {
            throw new NotFoundException(getNotFoundMessage());
        }

        leadRepository.updateEntityStatus(id, EntityStatus.INATIVO);

    }

    @Override
    public LeadGetDto getById(UUID id) {
        LeadEntity leadEntity = leadRepository.findById(id).orElseThrow(
                () -> new RuntimeException(getNotFoundMessage())
        );

        return leadMapper.leadEntityToDtoGet(leadEntity);
    }

    @Override
    public PagedModel<LeadGetDto> getAll(Pageable pageable) {
        Page<LeadGetDto> leads = leadRepository.findAllBy(pageable, LeadGetDto.class);
        return new PagedModel<LeadGetDto>(leads);
    }


    private String getNotFoundMessage() {
        return messageUtils.getMessage("leads.not-found");
    }
}
