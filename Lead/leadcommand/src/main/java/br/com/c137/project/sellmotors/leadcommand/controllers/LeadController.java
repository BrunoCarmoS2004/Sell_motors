package br.com.c137.project.sellmotors.leadcommand.controllers;

import br.com.c137.project.sellmotors.leadcommand.dtos.LeadDto;
import br.com.c137.project.sellmotors.leadcommand.services.LeadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping()
    ResponseEntity<LeadDto> create(@RequestBody LeadDto dto) {
        return ResponseEntity.ok(leadService.create(dto));
    }

    @PatchMapping()
    ResponseEntity<LeadDto> update(@RequestBody LeadDto dto){
        return ResponseEntity.ok(leadService.update(dto));
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable String id) {
        leadService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
