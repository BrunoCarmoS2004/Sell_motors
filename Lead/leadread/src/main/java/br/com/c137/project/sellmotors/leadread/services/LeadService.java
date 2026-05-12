package br.com.c137.project.sellmotors.leadread.services;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.List;
import java.util.UUID;

public interface LeadService {

    PagedModel<LeadDomain> findAllByTenantId(Pageable pageable);

    PagedModel<LeadDomain> listByNameLikeIgnoreCase(String name, Pageable pageable);

    PagedModel<LeadDomain> listByEmailLikeIgnoreCase(String email, Pageable pageable);
}
