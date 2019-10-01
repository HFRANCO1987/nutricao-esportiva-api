package br.com.projeto_mvp_app.projeto_mvp_app.exceptions.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<?> handleResouseNotFoundException(ValidacaoException rfnException) {
        ValidacaoExceptionDetails resourceNotFoundDetails = new ValidacaoExceptionDetails();
        resourceNotFoundDetails.setError("Bad Request");
        resourceNotFoundDetails.setTimestamp(new Date().getTime());
        resourceNotFoundDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        resourceNotFoundDetails.setMessage(rfnException.getMessage());
        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.NOT_FOUND);
    }
}
