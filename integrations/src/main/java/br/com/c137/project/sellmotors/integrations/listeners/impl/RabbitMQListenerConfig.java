package br.com.c137.project.sellmotors.integrations.listeners.impl;

import br.com.c137.project.sellmotors.integrations.listeners.ListenerConfig;
import br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.dtos.gets.VehicleGetDTO;
import br.com.c137.project.sellmotors.integrations.services.mercadolivre.IntegrationService;
import br.com.c137.project.sellmotors.integrations.utils.SyncLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQListenerConfig implements ListenerConfig {

    private final ObjectMapper objectMapper;

    private final IntegrationService integrationService;

    public RabbitMQListenerConfig(ObjectMapper objectMapper, IntegrationService integrationService) {
        this.objectMapper = objectMapper;
        this.integrationService = integrationService;
    }

    @Bean
    public Queue vehicleIntegrationsQueue() {
        return new Queue("vehicleIntegrationsQueue", true);
    }

    @RabbitListener(queues = "vehicleIntegrationsQueue")
    @Override
    public void listenToVehicleIntegrationQueue(String message) {
        try {
            VehicleGetDTO vehicle = objectMapper.readValue(message, VehicleGetDTO.class);
            integrationService.integration(vehicle);
            SyncLogger.info("Mensagem recebida da fila vehicleIntegrationsQueue: " + vehicle.toString());
        } catch (Exception e) {
            SyncLogger.error("Erro em ouvir a vehicleIntegrationsQueue " + e.getMessage());
        }
    }
}
