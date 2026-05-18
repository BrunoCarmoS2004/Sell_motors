package br.com.c137.project.sellmotors.integrations.controllers;

import br.com.c137.project.sellmotors.integrations.services.mercadolivre.UserIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration")
public class UserIntegrationController {

    private final UserIntegrationService userIntegrationService;

    public UserIntegrationController(UserIntegrationService userIntegrationService) {
        this.userIntegrationService = userIntegrationService;
    }

    @GetMapping("/mercadolivre/token/{tgCode}")
    ResponseEntity<Void> getTokenMercadoLivre(@PathVariable String tgCode) {
        userIntegrationService.getTokenMercadoLivre(tgCode);
        return ResponseEntity.ok().build();
    }
}
