package br.com.c137.project.sellmotors.integrations.services.mercadolivre.impl;

import br.com.c137.project.sellmotors.integrations.exceptions.MercadoLivreException;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets.MlTokenResponse;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.enums.PlataformNames;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models.UserIntegration;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.repositories.UserIntegrationRepository;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.repositories.UserTenantRepository;
import br.com.c137.project.sellmotors.integrations.services.mercadolivre.UserIntegrationService;
import br.com.c137.project.sellmotors.integrations.utils.MessageUtils;
import br.com.c137.project.sellmotors.integrations.utils.ServiceUtils;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;

@Service
public class UserIntegrationServiceImpl implements UserIntegrationService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final UserIntegrationRepository userIntegrationRepository;

    private final MessageUtils messageUtils;

    public UserIntegrationServiceImpl(UserIntegrationRepository userIntegrationRepository, MessageUtils messageUtils) {
        this.userIntegrationRepository = userIntegrationRepository;
        this.messageUtils = messageUtils;
    }

    public void getTokenMercadoLivre(String code) {
        String url = "https://api.mercadolibre.com/oauth/token";

        // 1. Definir os Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        // 2. Definir o Body (x-www-form-urlencoded usa MultiValueMap)
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", "6131383144595988");
        map.add("client_secret", "5iJ3z5ngPeodYFmgyAGTM7V0tMg9Gl1v");
        map.add("code", code);
        map.add("redirect_uri", "https://webhook.site/ade888fe-b6c5-490b-a42c-f01843024cbb");

        // 3. Montar a requisição
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // 4. Executar o POST
        try {
            ResponseEntity<MlTokenResponse> response = restTemplate.postForEntity(url, request, MlTokenResponse.class);
            if(response.getBody() == null) {
                throw new MercadoLivreException(messageUtils.getMessage("integration.mercadolivre.erro"));
            }
            LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(response.getBody().expiresIn());
            UserIntegration integration = UserIntegration.builder()
                    .tenantId(ServiceUtils.getUserIdFromToken())
                    .platformName(PlataformNames.MERCADO_LIVRE)
                    .accessToken(response.getBody().accessToken())
                    .refreshToken(response.getBody().refreshToken())
                    .expiresAt(expiresAt)
                    .accountId(response.getBody().userId())
                    .build();

            userIntegrationRepository.save(integration);

        } catch (Exception e) {
            // No seu sistema SellMotors, trate o erro de expiração de code aqui
            throw new RuntimeException("Erro ao obter token do ML: " + e.getMessage());
        }
    }
}
