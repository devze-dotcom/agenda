package com.agenda.refatoracao.exception;

public class ContatoJaExiste extends RuntimeException {
    public ContatoJaExiste(String mensagem){
        super(mensagem);
    }
}
