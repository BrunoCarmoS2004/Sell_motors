package br.com.c137.project.sellmotors.authcommand.security.controllers;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets.UserTokenGetDTO;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.posts.UserLoginPostDTO;
import br.com.c137.project.sellmotors.authcommand.security.responses.TokenResponse;
import br.com.c137.project.sellmotors.authcommand.security.services.TokenService;
import br.com.c137.project.sellmotors.authcommand.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginPostDTO userLoginPostDTO) {
        UserTokenGetDTO userTokenGetDTO = userService.getUserByEmailOrInscription(userLoginPostDTO.email(), userLoginPostDTO.inscription(), userLoginPostDTO.password());
        String token = tokenService.gerarToken(userTokenGetDTO);
        String refreshToken = tokenService.gerarRefreshToken(userTokenGetDTO);
        return ResponseEntity.ok(new TokenResponse(token, refreshToken));
    }
}
