package br.com.c137.project.sellmotors.vehiclecommand.services;

import br.com.c137.project.sellmotors.vehiclecommand.configurations.RabbitMqTopicConfig;
import br.com.c137.project.sellmotors.vehiclecommand.exceptions.RabbitMqSerializingException;
import br.com.c137.project.sellmotors.vehiclecommand.utils.MessageUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImpl implements BrokerService {

    private final MessageUtils messageUtils;

    private final ObjectMapper objectMapper;

    private final RabbitTemplate rabbitTemplate;

    private final RabbitMqTopicConfig rabbitMqTopicConfig;

    public RabbitMqServiceImpl(MessageUtils messageUtils, ObjectMapper objectMapper, RabbitTemplate rabbitTemplate, RabbitMqTopicConfig rabbitMqTopicConfig) {
        this.messageUtils = messageUtils;
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqTopicConfig = rabbitMqTopicConfig;
    }

    @Override
    public void send(String routingKey, Object data) {
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            rabbitTemplate.convertAndSend(rabbitMqTopicConfig.exchangeName, routingKey, jsonData, message -> {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            });
        } catch (JsonProcessingException e) {
            throw new RabbitMqSerializingException(messageUtils.getMessage("rabbitmq.serializing-error") + e.getMessage());
        }
    }
}
