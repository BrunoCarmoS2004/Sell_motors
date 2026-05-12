package br.com.c137.project.sellmotors.leadread.services.impl;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import br.com.c137.project.sellmotors.leadread.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.leadread.repositories.LeadRepository;
import br.com.c137.project.sellmotors.leadread.services.LeadService;
import br.com.c137.project.sellmotors.leadread.utils.MessageUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;

    private final MessageUtils messageUtils;

    public LeadServiceImpl(LeadRepository leadRepository, MessageUtils messageUtils) {
        this.leadRepository = leadRepository;
        this.messageUtils = messageUtils;
    }

    @Override
    public List<LeadDomain> findAllByTenantId(UUID tenantId) {
        try {
            return leadRepository.findAllByTenantId(tenantId);
        } catch (Exception e) {
            throw new NotFoundException(messageUtils.getMessage("leads.not-found"));
        }
    }

    @Override
    public List<LeadDomain> listByNameLikeIgnoreCase(String name) {
        return List.of();
    }

    @Override
    public List<LeadDomain> listByEmailLikeIgnoreCase(String email) {
        return List.of();
    }
}
