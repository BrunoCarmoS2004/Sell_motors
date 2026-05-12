package br.com.c137.project.sellmotors.leadread.services;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import br.com.c137.project.sellmotors.leadread.dtos.gets.LeadFullDto;
import br.com.c137.project.sellmotors.leadread.responses.ResponsePayload;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.List;
import java.util.UUID;

public interface LeadService {

    PagedModel<LeadFullDto> findAllByTenantId(Pageable pageable);

    PagedModel<LeadFullDto> listByNameLikeIgnoreCase(String name, Pageable pageable);

    PagedModel<LeadFullDto> listByEmailLikeIgnoreCase(String email, Pageable pageable);

    LeadFullDto findById(UUID id);
}
