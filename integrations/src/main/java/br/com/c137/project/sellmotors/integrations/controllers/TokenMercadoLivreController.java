package br.com.c137.project.sellmotors.integrations.controllers;

import br.com.c137.project.sellmotors.integrations.services.mercadolivre.TokenMercadoLivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth/mercadolivre")
public class TokenMercadoLivreController {
    private final TokenMercadoLivreService tokenMercadoLivreService;

    public TokenMercadoLivreController(TokenMercadoLivreService tokenMercadoLivreService) {
        this.tokenMercadoLivreService = tokenMercadoLivreService;
    }

    @GetMapping("/token/{tgCode}")
    ResponseEntity<Void> getTokenMercadoLivre(@PathVariable String tgCode) {
        tokenMercadoLivreService.getTokenMercadoLivre(tgCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refreshtoken/{tenantId}")
    ResponseEntity<Void> getRefreshTokenMercadoLivre(@PathVariable UUID tenantId) {
        tokenMercadoLivreService.getRefreshTokenMercadoLivre(tenantId);
        return ResponseEntity.ok().build();
    }
}
