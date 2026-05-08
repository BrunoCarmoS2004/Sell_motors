package br.com.c137.project.sellmotors.leadcommand.controllers;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.gets.LeadGetDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.posts.LeadPostDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.puts.LeadPutDto;
import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.models.LeadEntity;
import br.com.c137.project.sellmotors.leadcommand.responses.ResponsePayload;
import br.com.c137.project.sellmotors.leadcommand.services.LeadService;
import br.com.c137.project.sellmotors.leadcommand.utils.MessageUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.c137.project.sellmotors.leadcommand.utils.ServiceUtils.createResponse;

@RestController
@RequestMapping("/leads")
public class LeadController {

    private final LeadService leadService;

    private final MessageUtils messageUtils;

    public LeadController(LeadService leadService, MessageUtils messageUtils) {
        this.leadService = leadService;
        this.messageUtils = messageUtils;
    }

    @PostMapping()
    ResponseEntity<ResponsePayload<LeadGetDto>> create(@RequestBody LeadPostDto dto) {
        LeadGetDto response = leadService.create(dto);
        return createResponse(
                HttpStatus.CREATED,
                response.id(),
                response,
                messageUtils.getMessage("leads.created")
        );
    }

    @PatchMapping("/{id}")
    ResponseEntity<ResponsePayload<LeadGetDto>> update(@RequestBody LeadPutDto dto, @PathVariable UUID id){
        LeadGetDto response = leadService.update(dto, id);
        return createResponse(
                HttpStatus.OK,
                response.id(),
                response,
                messageUtils.getMessage("leads.updated")
        );
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        leadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    ResponseEntity<ResponsePayload<LeadGetDto>> getById(@PathVariable UUID id) {
        LeadGetDto response = leadService.getById(id);

        return createResponse(
                HttpStatus.OK,
                response.id(),
                response,
                messageUtils.getMessage("leads.founded")
        );
    }

    @GetMapping()
    ResponseEntity<PagedModel<LeadGetDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(leadService.getAll(pageable));

    }

}
