package br.com.pm.microsservico_externo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.sendgrid.Request;
import com.sendgrid.SendGrid;

import br.com.pm.microsservico_externo.exception.EmailNaoExisteException;
import br.com.pm.microsservico_externo.model.Email;
import br.com.pm.microsservico_externo.model.NovoEmail;

class ServicoEnvioEmailTest {

    @InjectMocks
    private ServicoEnvioEmail servicoEnvioEmail;

    @Mock
    private SendGrid sendGrid;

    @Mock
    private com.sendgrid.Response response;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveLancarExcecaoSeEmailNaoFornecido() {
        NovoEmail novoEmail = new NovoEmail();
        novoEmail.setEmail(null);
        
        assertThrows(EmailNaoExisteException.class, () -> {
            servicoEnvioEmail.enviarEmail(novoEmail);
        });
    }

    @Test
    public void deveEnviarEmailComSucesso() throws Exception {
        NovoEmail novoEmail = new NovoEmail();
        novoEmail.setEmail("teste@dominio.com");
        novoEmail.setAssunto("Assunto");
        novoEmail.setMensagem("Mensagem de teste");

        // Mockando o comportamento do SendGrid
        when(sendGrid.api(any(Request.class))).thenReturn(response);
        when(response.getStatusCode()).thenReturn(200);

        // Chama o método para enviar e-mail
        Email emailEnviado = servicoEnvioEmail.enviarEmail(novoEmail);

        assertNotNull(emailEnviado);
        assertEquals("teste@dominio.com", emailEnviado.getEmail());
        assertEquals("Assunto", emailEnviado.getAssunto());
        assertEquals("Mensagem de teste", emailEnviado.getMensagem());
    }
}

