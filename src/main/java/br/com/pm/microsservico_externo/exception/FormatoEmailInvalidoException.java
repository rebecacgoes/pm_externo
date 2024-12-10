package br.com.pm.microsservico_externo.exception;

public class FormatoEmailInvalidoException extends RuntimeException {

    public FormatoEmailInvalidoException(String mensagem) {
        super(mensagem);
    }
}
