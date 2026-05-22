package br.com.c137.project.sellmotors.vehiclecommand.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqTopicConfig {
    public final String exchangeName = "sellmotorsExchange";

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Declarables bindingSyncAndIntegration(
            Queue vehicleQueue,
            Queue vehicleIntegrationsQueue,
            TopicExchange exchange
    ){
        String routingKey = "vehicle.sync.integration.#";
        return new Declarables(
                BindingBuilder.bind(vehicleQueue).to(exchange).with(routingKey),
                BindingBuilder.bind(vehicleIntegrationsQueue).to(exchange).with(routingKey)
        );
    }

    @Bean
    public Binding bindingVehicleIntegrations(Queue vehicleIntegrationsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(vehicleIntegrationsQueue).to(exchange).with("vehicle.integration.#");
    }


    @Bean
    public Queue vehicleQueue() {
        return new Queue("vehicleSyncQueue", true);
    }

    @Bean
    public Queue vehicleIntegrationsQueue() {
        return new Queue("vehicleIntegrationsQueue", true);
    }
}
