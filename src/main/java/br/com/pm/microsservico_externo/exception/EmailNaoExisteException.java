package br.com.pm.microsservico_externo.exception;

public class EmailNaoExisteException extends RuntimeException {

    public EmailNaoExisteException(String mensagem) {
        super(mensagem);
    }
} 
