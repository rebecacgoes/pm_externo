package br.com.pm.microsservico_externo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.com.pm.microsservico_externo.exception.EmailNaoExisteException;
import br.com.pm.microsservico_externo.exception.FormatoEmailInvalidoException;
import br.com.pm.microsservico_externo.model.Email;
import br.com.pm.microsservico_externo.model.NovoEmail;
import br.com.pm.microsservico_externo.service.ServicoEnvioEmail;

@ExtendWith(MockitoExtension.class)  // Adicionando a anotação para habilitar o Mockito
class EmailControllerTest {

    @Mock
    private ServicoEnvioEmail servicoEnvioEmail;

    @InjectMocks
    private EmailController emailController;

    private NovoEmail novoEmail;
    private Email emailEnviado;

    @BeforeEach
    void setUp() {
        // Preparar dados de teste
        novoEmail = new NovoEmail();
        novoEmail.setEmail("test@example.com");

        emailEnviado = new Email();
        emailEnviado.setEmail("test@example.com");
    }

    @Test
    void deveEnviarEmailComSucesso() throws Exception {
        // Arrange
        when(servicoEnvioEmail.enviarEmail(novoEmail)).thenReturn(emailEnviado);

        // Act
        ResponseEntity<Email> response = emailController.enviarEmail(novoEmail);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("test@example.com", response.getBody().getEmail());
    }

    @Test
    void deveRetornar404SeEmailNaoExiste() throws Exception {
        // Arrange
        when(servicoEnvioEmail.enviarEmail(novoEmail)).thenThrow(EmailNaoExisteException.class);

        // Act
        ResponseEntity<Email> response = emailController.enviarEmail(novoEmail);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void deveRetornar422SeFormatoEmailInvalido() throws Exception {
        // Arrange
        when(servicoEnvioEmail.enviarEmail(novoEmail)).thenThrow(FormatoEmailInvalidoException.class);

        // Act
        ResponseEntity<Email> response = emailController.enviarEmail(novoEmail);

        // Assert
        assertEquals(422, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
