package br.com.c137.project.sellmotors.vehicleread.controllers;

import br.com.c137.project.sellmotors.vehicleread.dtos.full.VehicleFullDTO;
import br.com.c137.project.sellmotors.vehicleread.responses.ResponsePayload;
import br.com.c137.project.sellmotors.vehicleread.services.VehicleService;
import br.com.c137.project.sellmotors.vehicleread.utils.MessageUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static br.com.c137.project.sellmotors.vehicleread.utils.ServiceUtils.createResponse;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    private final MessageUtils messageUtils;


    public VehicleController(VehicleService vehicleService, MessageUtils messageUtils) {
        this.vehicleService = vehicleService;
        this.messageUtils = messageUtils;
    }

    @GetMapping()
    public ResponseEntity<PagedModel<VehicleFullDTO>> findAllByCreatedBy(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findAllByCreatedBy(pageable));
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<PagedModel<VehicleFullDTO>> findAllByModeloLikeIgnoreCase(
            @PathVariable String modelo,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findAllByModeloLikeIgnoreCase(modelo, pageable));
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<PagedModel<VehicleFullDTO>> findAllByMarcaLikeIgnoreCase(
            @PathVariable String marca,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findAllByMarcaLikeIgnoreCase(marca, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload<VehicleFullDTO>> findVehicleFullById(@PathVariable UUID id) {
        VehicleFullDTO vehicleFullDTO = vehicleService.findVehicleFullById(id);
        return createResponse(
                HttpStatus.OK,
                vehicleFullDTO.id(),
                vehicleFullDTO,
                messageUtils.getMessage("vehicle.found")
        );
    }
}
