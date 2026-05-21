package br.com.c137.project.sellmotors.integrations.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class ServiceUtils {

    public static final String URL_BASE_MERCADO_LIVRE = "https://api.mercadolibre.com";

    public static final String URL_BASE_MERCADO_LIVRE_TOKEN = URL_BASE_MERCADO_LIVRE+"/oauth/token";

    public static final String URL_BASE_MERCADO_LIVRE_ITENS = URL_BASE_MERCADO_LIVRE+"/items";

    public static UUID getUserIdFromToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
            return UUID.fromString(jwt.getSubject());
        }
        return null;
    }
}
