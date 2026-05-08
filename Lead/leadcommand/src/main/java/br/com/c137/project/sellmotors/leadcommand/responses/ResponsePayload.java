package br.com.c137.project.sellmotors.leadcommand.responses;

import java.util.UUID;

public record ResponsePayload<T>(UUID id,
                              String message,
                              T data) {
}
