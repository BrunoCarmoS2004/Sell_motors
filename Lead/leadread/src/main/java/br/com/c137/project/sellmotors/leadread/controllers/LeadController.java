package br.com.c137.project.sellmotors.leadread.controllers;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import br.com.c137.project.sellmotors.leadread.responses.ResponsePayload;
import br.com.c137.project.sellmotors.leadread.services.LeadService;
import br.com.c137.project.sellmotors.leadread.utils.MessageUtils;
import br.com.c137.project.sellmotors.leadread.utils.ServiceUtils;
import jakarta.ws.rs.PathParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lead")
public class LeadController {

    private final LeadService leadService;

    private final MessageUtils messageUtils;

    public LeadController(LeadService leadService, MessageUtils messageUtils) {
        this.leadService = leadService;
        this.messageUtils = messageUtils;
    }

    @GetMapping()
    ResponseEntity<PagedModel<LeadDomain>> listAllLeads(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(leadService.findAllByTenantId(pageable));
    }

    @GetMapping("/name/{name}")
    ResponseEntity<PagedModel<LeadDomain>> listAllLeadsByName(@PathVariable String name, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(leadService.listByNameLikeIgnoreCase(name, pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponsePayload<LeadDomain>> findById(@PathVariable UUID id) {
        LeadDomain lead = leadService.findById(id);
        return ServiceUtils.createResponse(HttpStatus.OK, lead.getId(), lead, messageUtils.getMessage("leads.founded"));
    }
}
