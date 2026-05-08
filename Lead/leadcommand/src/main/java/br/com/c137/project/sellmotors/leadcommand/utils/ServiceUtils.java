package br.com.c137.project.sellmotors.leadcommand.utils;

import br.com.c137.project.sellmotors.leadcommand.responses.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class ServiceUtils {

    public static <T> ResponseEntity<ResponsePayload<T>> createResponse(HttpStatus httpStatus, UUID id, T dto, String message) {
        return ResponseEntity.status(httpStatus).body(new ResponsePayload<>(id, message, dto));
    }
}
