package br.com.c137.project.sellmotors.leadcommand.exceptions;

public class TenantSchemaNotReadyException extends RuntimeException {
	public TenantSchemaNotReadyException(String message) {
        super(message);
    }
}
