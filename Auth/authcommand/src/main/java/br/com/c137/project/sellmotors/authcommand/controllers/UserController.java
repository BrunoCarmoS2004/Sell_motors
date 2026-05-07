package br.com.c137.project.sellmotors.authcommand.controllers;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets.UserGetDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.posts.UserPostDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.puts.UserPutDTO;
import br.com.c137.project.sellmotors.authcommand.responses.ResponsePayload;
import br.com.c137.project.sellmotors.authcommand.services.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUtils messageUtils;

    @GetMapping
    public ResponseEntity<PagedModel<UserGetDTO>> getAll(@PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload<UserGetDTO>> getUserById(@PathVariable UUID id) {
        UserGetDTO userGetDTO = userService.getUserById(id);
        return createResponse(
                HttpStatus.OK,
                userGetDTO.id(),
                userGetDTO,
                messageUtils.getMessage("user.found")
        );
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<UserGetDTO>> postUser(@Valid @RequestBody UserPostDTO userPostDTO) {
        UserGetDTO userGetDTO = userService.postUser(userPostDTO);
        return createResponse(
                HttpStatus.CREATED,
                userGetDTO.id(),
                userGetDTO,
                messageUtils.getMessage("user.created")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePayload<UserGetDTO>> putUser(@PathVariable UUID id, @Valid @RequestBody UserPutDTO userPutDTO) {
        UserGetDTO userGetDTO = userService.putUser(id, userPutDTO);
        return createResponse(
                HttpStatus.OK,
                userGetDTO.id(),
                userGetDTO,
                messageUtils.getMessage("user.updated")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> inactiveUser(@PathVariable UUID id) {
        userService.inactiveUser(id);
        return ResponseEntity.noContent().build();
    }
}
