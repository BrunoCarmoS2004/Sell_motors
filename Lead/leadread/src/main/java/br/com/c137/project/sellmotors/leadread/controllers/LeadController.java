package br.com.c137.project.sellmotors.leadread.controllers;

import br.com.c137.project.sellmotors.leadread.domains.leads.LeadDomain;
import br.com.c137.project.sellmotors.leadread.services.LeadService;
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

    @GetMapping("/{tenantId}")
    ResponseEntity<List<LeadDomain>> listAllLeads(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(leadService.findAllByTenantId(tenantId));
    }
}
