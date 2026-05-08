package br.com.c137.project.sellmotors.vehiclecommand.controllers;

import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.gets.VehicleGetDTO;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.posts.VehiclePostDTO;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.puts.VehiclePutDTO;
import br.com.c137.project.sellmotors.vehiclecommand.responses.ResponsePayload;
import br.com.c137.project.sellmotors.vehiclecommand.services.MessageUtils;
import br.com.c137.project.sellmotors.vehiclecommand.services.VehicleService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.c137.project.sellmotors.vehiclecommand.utils.ServiceUtils.createResponse;

@RestController
@RequestMapping("/vehicle")
public class VehicleControllers {
    private final VehicleService vehicleService;

    private final MessageUtils messageUtils;

    public VehicleControllers(VehicleService vehicleService, MessageUtils messageUtils) {
        this.vehicleService = vehicleService;
        this.messageUtils = messageUtils;
    }

    @GetMapping
    public ResponseEntity<PagedModel<VehicleGetDTO>> getAll(@PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(vehicleService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload<VehicleGetDTO>> getVehicleById(@PathVariable UUID id) {
        VehicleGetDTO vehicleGetDTO = vehicleService.getVehicleById(id);
        return createResponse(
                HttpStatus.OK,
                vehicleGetDTO.id(),
                vehicleGetDTO,
                messageUtils.getMessage("vehicle.found")
        );
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<VehicleGetDTO>> postVehicle(@RequestBody VehiclePostDTO vehiclePostDTO) {
        VehicleGetDTO vehicleGetDTO = vehicleService.postVehicle(vehiclePostDTO);
        return createResponse(
                HttpStatus.CREATED,
                vehicleGetDTO.id(),
                vehicleGetDTO,
                messageUtils.getMessage("vehicle.created")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePayload<VehicleGetDTO>> putVehicle(@PathVariable UUID id, @RequestBody VehiclePutDTO vehiclePutDTO) {
        VehicleGetDTO vehicleGetDTO = vehicleService.putVehicle(id, vehiclePutDTO);
        return createResponse(
                HttpStatus.OK,
                vehicleGetDTO.id(),
                vehicleGetDTO,
                messageUtils.getMessage("vehicle.updated")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> putVehicle(@PathVariable UUID id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
