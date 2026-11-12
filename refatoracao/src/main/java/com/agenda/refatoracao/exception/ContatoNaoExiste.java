package com.agenda.refatoracao.exception;

public class ContatoNaoExiste extends RuntimeException {
    public ContatoNaoExiste(String mensagem){
        super(mensagem);
    }
    
}
