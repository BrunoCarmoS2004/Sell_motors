package br.com.c137.project.sellmotors.leadcommand.exceptions;

public class RabbitMqSerializingException extends RuntimeException {
    public RabbitMqSerializingException(String message) {
        super(message);
    }
}
