package br.com.c137.project.sellmotors.vehicleread.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorView handleNotFoundException(HttpServletRequest req, NotFoundException e) {
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ErrorView(
                httpStatus.value(),
                e.getMessage(),
                httpStatus.name(),
                req.getServletPath()
        );
    }

    @ExceptionHandler(value = TokenValidationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView handleTokenValidationException(HttpServletRequest req, TokenValidationException e) {
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ErrorView(
                httpStatus.value(),
                e.getMessage(),
                httpStatus.name(),
                req.getServletPath()
        );
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView handleRuntimeException(HttpServletRequest req, RuntimeException e) {
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ErrorView(
                httpStatus.value(),
                e.getMessage(),
                httpStatus.name(),
                req.getServletPath()
        );
    }
}
