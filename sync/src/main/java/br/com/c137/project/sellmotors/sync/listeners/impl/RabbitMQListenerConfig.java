package br.com.c137.project.sellmotors.sync.listeners.impl;

import br.com.c137.project.sellmotors.sync.dtos.domains.LeadDto;
import br.com.c137.project.sellmotors.sync.dtos.domains.VehicleDto;
import br.com.c137.project.sellmotors.sync.listeners.ListenerConfig;
import br.com.c137.project.sellmotors.sync.services.SyncService;
import br.com.c137.project.sellmotors.sync.utils.SyncLogger;
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

    private final SyncService syncService;

    public RabbitMQListenerConfig(ObjectMapper objectMapper, SyncService syncService) {
        this.objectMapper = objectMapper;
        this.syncService = syncService;
    }

    @Bean
    public Queue leadQueue() {
        return new Queue("leadQueue", true);
    }

    @Bean
    public Queue vehicleQueue() {
        return new Queue("vehicleSyncQueue", true);
    }

    @RabbitListener(queues = "leadQueue")
    @Override
    public void listenToLeadQueue(String message) {
        try {
            LeadDto lead = objectMapper.readValue(message, LeadDto.class);

            syncService.syncLead(lead);

            SyncLogger.info("Mensagem recebida da fila leadQueue: " + lead.toString());
        } catch (Exception e) {
            SyncLogger.error("Erro em ouvir a leadQueue " + e.getMessage());
        }

    }

    @RabbitListener(queues = "vehicleSyncQueue")
    @Override
    public void listenToVehicleQueue(String message) {
        try {
            VehicleDto vehicle = objectMapper.readValue(message, VehicleDto.class);

            syncService.syncVehicle(vehicle);

            SyncLogger.info("Mensagem recebida da fila vehicleQueue: " + vehicle.toString());
        } catch (Exception e) {
            SyncLogger.error("Erro em ouvir a vehicleQueue " + e.getMessage());
        }

    }
}
