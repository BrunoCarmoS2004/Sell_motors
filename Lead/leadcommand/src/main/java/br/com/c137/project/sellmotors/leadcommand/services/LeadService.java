package br.com.c137.project.sellmotors.leadcommand.services;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.gets.LeadGetDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.posts.LeadPostDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.puts.LeadPutDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.List;
import java.util.UUID;

public interface LeadService {
    LeadGetDto create(LeadPostDto dto);
    LeadGetDto update(LeadPutDto dto, UUID id);
    void deleteById(UUID id);
    LeadGetDto getById(UUID id);
    PagedModel<LeadGetDto> getAll(Pageable pageable);
}
