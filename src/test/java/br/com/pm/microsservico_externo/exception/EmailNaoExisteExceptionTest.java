package br.com.pm.microsservico_externo.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class EmailNaoExisteExceptionTest {

    @Test
    public void deveLancarExcecaoComMensagemCorreta() {
        // Mensagem esperada
        String mensagem = "Email não encontrado";
        
        // Verifica se a exceção é lançada com a mensagem correta
        EmailNaoExisteException exception = assertThrows(EmailNaoExisteException.class, () -> {
            throw new EmailNaoExisteException(mensagem);
        });

        // Verifica se a mensagem da exceção é a mesma que a fornecida
        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoSemMensagem() {
        // Verifica se a exceção é lançada sem mensagem
        EmailNaoExisteException exception = assertThrows(EmailNaoExisteException.class, () -> {
            throw new EmailNaoExisteException(null);
        });

        // Verifica se a mensagem está nula ou vazia
        assertEquals(null, exception.getMessage());
    }
}

