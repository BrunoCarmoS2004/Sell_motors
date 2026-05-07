package br.com.c137.project.sellmotors.authcommand.controllers;

import br.com.c137.project.sellmotors.authcommand.responses.ResponsePayload;
import br.com.c137.project.sellmotors.authcommand.services.UserTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usertenant")
public class UserTenantContoller {

    @Autowired
    private UserTenantService userTenantService;

    @PostMapping("/create/database/{userId}")
    public ResponseEntity<ResponsePayload<String>> createUserTenant(@PathVariable UUID userId) {
        userTenantService.createUserTenant(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePayload<>(userId, "Data base created.", "user_"+userId.toString()));
    }

    @DeleteMapping("/delete/database/{userId}")
    public ResponseEntity<ResponsePayload<String>> deleteUserTenant(@PathVariable UUID userId) {
        userTenantService.deleteUserTenant(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponsePayload<>(userId, "Data base deleted.", "user_"+userId.toString()));
    }

    @DeleteMapping("/inactive/database/{userId}")
    public ResponseEntity<ResponsePayload<String>> inactiveUserTenant(@PathVariable UUID userId) {
        userTenantService.inactiveUserTenant(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponsePayload<>(userId, "Data base inactived.", "user_"+userId.toString()));
    }

    @DeleteMapping("/return/database/{userId}")
    public ResponseEntity<ResponsePayload<String>> returnUserTenant(@PathVariable UUID userId) {
        userTenantService.returnToCreatedUserTenant(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponsePayload<>(userId, "Data base returned.", "user_"+userId.toString()));
    }
}
