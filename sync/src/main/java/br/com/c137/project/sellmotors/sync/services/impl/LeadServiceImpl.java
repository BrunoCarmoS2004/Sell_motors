package br.com.c137.project.sellmotors.sync.services.impl;

import br.com.c137.project.sellmotors.sync.dtos.domains.LeadDto;
import br.com.c137.project.sellmotors.sync.repositores.LeadRepository;
import br.com.c137.project.sellmotors.sync.services.LeadService;
import br.com.c137.project.sellmotors.sync.utils.MessageUtils;
import br.com.c137.project.sellmotors.sync.utils.SyncLogger;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;
    private final MessageUtils messageUtils;

    public LeadServiceImpl(LeadRepository leadRepository, MessageUtils messageUtils) {
        this.leadRepository = leadRepository;
        this.messageUtils = messageUtils;
    }

    @Override
    public void saveLead(LeadDto dto) {
        try {
            SyncLogger.info(messageUtils.getMessage("sync.saving-lead", dto.id()));
            leadRepository.save(dto);
        } catch (Exception e) {
            SyncLogger.error(messageUtils.getMessage("sync.saving-lead-error", e.getMessage()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
