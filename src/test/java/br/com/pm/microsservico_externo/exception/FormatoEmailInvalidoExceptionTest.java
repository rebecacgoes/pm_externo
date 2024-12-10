package br.com.pm.microsservico_externo.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FormatoEmailInvalidoExceptionTest {

    @Test
    public void deveLancarExcecaoComMensagemCorreta() {
        // Mensagem esperada
        String mensagem = "Formato de email inválido";
        
        // Verifica se a exceção é lançada com a mensagem correta
        FormatoEmailInvalidoException exception = assertThrows(FormatoEmailInvalidoException.class, () -> {
            throw new FormatoEmailInvalidoException(mensagem);
        });

        // Verifica se a mensagem da exceção é a mesma que a fornecida
        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoSemMensagem() {
        // Verifica se a exceção é lançada sem mensagem
        FormatoEmailInvalidoException exception = assertThrows(FormatoEmailInvalidoException.class, () -> {
            throw new FormatoEmailInvalidoException(null);
        });

        // Verifica se a mensagem está nula ou vazia
        assertEquals(null, exception.getMessage());
    }
}

