package com.agenda.refatoracao.exception;

import java.time.LocalDateTime;

import org.hibernate.event.spi.LockEventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContatoJaExiste.class)
    public ResponseEntity<ErroResponse> handleContatoJaExiste(ContatoJaExiste exception) {

        ErroResponse erro = new ErroResponse(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erro);

    }

    @ExceptionHandler(ContatoNaoExiste.class)
    public ResponseEntity<ErroResponse> handleContatoNaoExiste(
            ContatoNaoExiste exception) {

        ErroResponse erro = new ErroResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erro);
    }

}
