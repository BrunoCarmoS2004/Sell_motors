package br.com.c137.project.sellmotors.leadread.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
}
