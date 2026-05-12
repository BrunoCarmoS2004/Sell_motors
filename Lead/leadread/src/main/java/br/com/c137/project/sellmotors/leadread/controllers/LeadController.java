package br.com.c137.project.sellmotors.leadread.controllers;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import br.com.c137.project.sellmotors.leadread.services.LeadService;
import jakarta.ws.rs.PathParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lead")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping()
    ResponseEntity<PagedModel<LeadDomain>> listAllLeads(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(leadService.findAllByTenantId(pageable));
    }

    @GetMapping("/name/{name}")
    ResponseEntity<PagedModel<LeadDomain>> listAllLeadsByName(@PathVariable String name, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(leadService.listByNameLikeIgnoreCase(name, pageable));
    }
}
