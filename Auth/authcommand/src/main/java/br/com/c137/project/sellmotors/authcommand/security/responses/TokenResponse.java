package br.com.c137.project.sellmotors.authcommand.security.responses;

public record TokenResponse(
        String token,
        String refreshToken
) {
}
