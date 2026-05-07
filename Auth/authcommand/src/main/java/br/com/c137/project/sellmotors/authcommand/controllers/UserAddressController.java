package br.com.c137.project.sellmotors.authcommand.controllers;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets.UserAddressGetDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.posts.UserAddressPostDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.puts.UserAddressPutDTO;
import br.com.c137.project.sellmotors.authcommand.responses.ResponsePayload;
import br.com.c137.project.sellmotors.authcommand.services.UserAddressService;
import br.com.c137.project.sellmotors.authcommand.utils.MessageUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.c137.project.sellmotors.authcommand.utils.ServiceUtils.createResponse;


@RestController
@RequestMapping("/useraddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private MessageUtils messageUtils;

    @GetMapping
    public ResponseEntity<PagedModel<UserAddressGetDTO>> getAll(@PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userAddressService.getAll(pageable));
    }

    @GetMapping("/useraddressof/{id}")
    public ResponseEntity<PagedModel<UserAddressGetDTO>> getAllByUserAddressOfId(@PathVariable UUID id, @PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userAddressService.getAllByUserAddressOf(id, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload<UserAddressGetDTO>> getUserAddressById(@PathVariable UUID id) {
        UserAddressGetDTO userAddressGetDTO = userAddressService.getUserAddressById(id);
        return createResponse(
                HttpStatus.OK,
                userAddressGetDTO.id(),
                userAddressGetDTO,
                messageUtils.getMessage("user.address.found")
        );
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<UserAddressGetDTO>> postUserAddress(@Valid @RequestBody UserAddressPostDTO userAddressPostDTO) {
        UserAddressGetDTO userAddressGetDTO = userAddressService.postUserAddress(userAddressPostDTO);
        return createResponse(
                HttpStatus.CREATED,
                userAddressGetDTO.id(),
                userAddressGetDTO,
                messageUtils.getMessage("user.address.created")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePayload<UserAddressGetDTO>> putUserAddress(@PathVariable UUID id, @Valid @RequestBody UserAddressPutDTO userAddressPutDTO) {
        UserAddressGetDTO userAddressGetDTO = userAddressService.putUserAddress(id, userAddressPutDTO);
        return createResponse(
                HttpStatus.OK,
                userAddressGetDTO.id(),
                userAddressGetDTO,
                messageUtils.getMessage("user.address.updated")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAddress(@PathVariable UUID id) {
        userAddressService.deleteUserAddress(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> inactiveUserAddress(@PathVariable UUID id) {
        userAddressService.inactiveUserAddress(id);
        return ResponseEntity.noContent().build();
    }
}
