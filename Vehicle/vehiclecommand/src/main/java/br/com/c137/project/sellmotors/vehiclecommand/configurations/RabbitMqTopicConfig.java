package br.com.c137.project.sellmotors.vehiclecommand.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
    public Binding bindingLead(Queue leadQueue, TopicExchange exchange) {
        return BindingBuilder.bind(leadQueue).to(exchange).with("vehicle.#");
    }

    @Bean
    public Queue leadQueue() {
        return new Queue("vehicleQueue", true);
    }
}
