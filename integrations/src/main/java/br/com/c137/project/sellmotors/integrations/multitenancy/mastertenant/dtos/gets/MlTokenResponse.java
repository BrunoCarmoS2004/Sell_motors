package br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MlTokenResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("refresh_token") String refreshToken,
        @JsonProperty("expires_in") Long expiresIn,
        @JsonProperty("user_id") String userId
) {
}