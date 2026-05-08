package br.com.c137.project.sellmotors.vehiclecommand.exceptions;

public class TenantSchemaNotReadyException extends RuntimeException {
	public TenantSchemaNotReadyException(String message) {
        super(message);
    }
}
