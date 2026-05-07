package br.com.c137.project.sellmotors.authcommand.security.controllers;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JwkSetController {
    @Autowired
    private JWKSet jwkSet;

    @GetMapping("/oauth2/jwks")
    public Map<String, Object> keys() {
        return this.jwkSet.toJSONObject();
    }
}
