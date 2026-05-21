package br.com.c137.project.sellmotors.integrations.services.mercadolivre.impl;

import br.com.c137.project.sellmotors.integrations.exceptions.MercadoLivreException;
import br.com.c137.project.sellmotors.integrations.exceptions.NotFoundException;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets.MlTokenResponse;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.enums.PlataformNames;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.UserIntegration;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.repositories.UserIntegrationRepository;
import br.com.c137.project.sellmotors.integrations.services.mercadolivre.TokenMercadoLivreService;
import br.com.c137.project.sellmotors.integrations.utils.MessageUtils;
import br.com.c137.project.sellmotors.integrations.utils.ServiceUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.c137.project.sellmotors.integrations.utils.ServiceUtils.URL_BASE_MERCADO_LIVRE;
import static br.com.c137.project.sellmotors.integrations.utils.ServiceUtils.URL_BASE_MERCADO_LIVRE_TOKEN;

@Service
public class TokenMercadoLivreServiceImpl implements TokenMercadoLivreService {
    private final RestTemplate restTemplate = new RestTemplate();

    private final UserIntegrationRepository userIntegrationRepository;

    private final MessageUtils messageUtils;

    public TokenMercadoLivreServiceImpl(UserIntegrationRepository userIntegrationRepository, MessageUtils messageUtils) {
        this.userIntegrationRepository = userIntegrationRepository;
        this.messageUtils = messageUtils;
    }

    @Override
    public void getTokenMercadoLivre(String code) {

        MlTokenResponse mlTokenResponse = getAccessTokenOrRefreshTokenMercadoLivre(code, "authorization_code");

        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(mlTokenResponse.expiresIn());
        UserIntegration integration = UserIntegration.builder()
                .tenantId(ServiceUtils.getUserIdFromToken())
                .platformName(PlataformNames.MERCADO_LIVRE)
                .accessToken(mlTokenResponse.accessToken())
                .refreshToken(mlTokenResponse.refreshToken())
                .expiresAt(expiresAt)
                .accountId(mlTokenResponse.userId())
                .build();

        userIntegrationRepository.save(integration);
    }

    @Override
    public void getRefreshTokenMercadoLivre(UUID tenantId) {
        UserIntegration userIntegration = userIntegrationRepository.findByTenantIdAndPlatformName(tenantId, PlataformNames.MERCADO_LIVRE)
                .orElseThrow(() -> new NotFoundException(messageUtils.getMessage("user.integration.not-found")));
        MlTokenResponse mlTokenResponse = getAccessTokenOrRefreshTokenMercadoLivre(userIntegration.getRefreshToken(), "refresh_token");

        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(mlTokenResponse.expiresIn());
        userIntegration.setAccessToken(mlTokenResponse.accessToken());
        userIntegration.setRefreshToken(mlTokenResponse.refreshToken());
        userIntegration.setExpiresAt(expiresAt);
        userIntegrationRepository.save(userIntegration);
    }

    @Override
    public MlTokenResponse getAccessTokenOrRefreshTokenMercadoLivre(String code, String grantType) {
        // 1. Definir os Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        // 2. Definir o Body (x-www-form-urlencoded usa MultiValueMap)
        HttpEntity<MultiValueMap<String, String>> request = getMultiValueMapHttpEntity(code, grantType, headers);

        // 4. Executar o POST
        try {
            ResponseEntity<MlTokenResponse> response = restTemplate.postForEntity(
                    URL_BASE_MERCADO_LIVRE_TOKEN,
                    request,
                    MlTokenResponse.class);
            if (response.getBody() == null) {
                throw new MercadoLivreException(messageUtils.getMessage("integration.mercadolivre.erro"));
            }
            return response.getBody();

        } catch (Exception e) {
            // No seu sistema SellMotors, trate o erro de expiração de code aqui
            throw new RuntimeException("Erro ao obter token do ML: " + e.getMessage());
        }
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueMapHttpEntity(String code, String grantType, HttpHeaders headers) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", grantType);
        map.add("client_id", "6131383144595988");
        map.add("client_secret", "5iJ3z5ngPeodYFmgyAGTM7V0tMg9Gl1v");
        map.add(tokenType(grantType) ? "code": "refresh_token" , code);
        map.add("redirect_uri", "https://webhook.site/ade888fe-b6c5-490b-a42c-f01843024cbb");

        // 3. Montar a requisição
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return request;
    }

    private boolean tokenType(String grantType) {
        return grantType.equals("authorization_code");
    }
}
