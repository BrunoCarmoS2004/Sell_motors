package br.com.c137.project.sellmotors.vehiclecommand.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
