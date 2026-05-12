package br.com.c137.project.sellmotors.leadread.services.impl;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import br.com.c137.project.sellmotors.leadread.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.leadread.exceptions.TokenValidationException;
import br.com.c137.project.sellmotors.leadread.repositories.LeadRepository;
import br.com.c137.project.sellmotors.leadread.services.LeadService;
import br.com.c137.project.sellmotors.leadread.utils.MessageUtils;
import br.com.c137.project.sellmotors.leadread.utils.ServiceUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
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
    public PagedModel<LeadDomain> findAllByTenantId(Pageable pageable) {
        try {
            UUID uuid = ServiceUtils.getUserIdFromToken();
            if(uuid == null) {
                throw new TokenValidationException(messageUtils.getMessage("leads.token-validation-error"));
            }
            return new PagedModel<>(leadRepository.findAllByCreatedBy(uuid, pageable));
        } catch (Exception e) {
            throw new NotFoundException(messageUtils.getMessage("leads.not-found") + e.getMessage());
        }
    }

    @Override
    public PagedModel<LeadDomain> listByNameLikeIgnoreCase(String name, UUID createdBy, Pageable pageable) {
        try {
            return new PagedModel<>(leadRepository.findByNameLikeIgnoreCase(name, createdBy, pageable));
        } catch (Exception e) {
            throw new NotFoundException(messageUtils.getMessage("leads.not-found") + e.getMessage());
        }

    }

    @Override
    public PagedModel<LeadDomain> listByEmailLikeIgnoreCase(String email, UUID createdBy, Pageable pageable) {
        try {
            return new PagedModel<>(leadRepository.findByEmailLikeIgnoreCase(email, createdBy, pageable));
        } catch (Exception e) {
            throw new NotFoundException(messageUtils.getMessage("leads.not-found") + e.getMessage());
        }

    }
}
