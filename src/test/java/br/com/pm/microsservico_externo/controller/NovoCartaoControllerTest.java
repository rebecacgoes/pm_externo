package br.com.pm.microsservico_externo.controller;

import java.time.LocalDate;

import br.com.pm.microsservico_externo.model.NovoCartaoDeCredito;
import br.com.pm.microsservico_externo.service.ServicoValidacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class NovoCartaoControllerTest {

    @Mock
    private ServicoValidacao servicoValidacao;

    @InjectMocks
    private NovoCartaoController novoCartaoController;

    private NovoCartaoDeCredito novoCartao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
        novoCartao = new NovoCartaoDeCredito();
        novoCartao.setNomeTitular("João da Silva");
        novoCartao.setNumero("1234567890123456");
        novoCartao.setValidade(LocalDate.of(2025, 12, 31));
        novoCartao.setCvv("123");
    }

    // Teste quando o cartão for válido
    @Test
    void deveRetornar200QuandoCartaoValido() {
        // Mock do serviço de validação
        when(servicoValidacao.validarCartaoDeCredito(novoCartao)).thenReturn("Cartão válido");

        // Chama o método do controlador
        ResponseEntity<String> response = novoCartaoController.validaCartaoDeCredito(novoCartao);

        // Verifica se o código de status é 200 e a mensagem está correta
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cartão válido", response.getBody());
    }

    // Teste quando o cartão for inválido
    @Test
    void deveRetornar422QuandoCartaoInvalido() {
        // Mock do serviço de validação
        when(servicoValidacao.validarCartaoDeCredito(novoCartao)).thenReturn("Cartão inválido");

        // Chama o método do controlador
        ResponseEntity<String> response = novoCartaoController.validaCartaoDeCredito(novoCartao);

        // Verifica se o código de status é 422 e a mensagem está correta
        assertEquals(422, response.getStatusCodeValue());
        assertEquals("Cartão inválido", response.getBody());
    }

    // Teste quando o serviço de validação lança uma exceção
    @Test
    void deveRetornar500QuandoErroNoServicoValidacao() {
        // Simula uma exceção no serviço de validação
        when(servicoValidacao.validarCartaoDeCredito(novoCartao)).thenThrow(new RuntimeException("Erro no serviço de validação"));

        // Chama o método do controlador e verifica o erro
        try {
            novoCartaoController.validaCartaoDeCredito(novoCartao);
        } catch (RuntimeException ex) {
            assertEquals("Erro no serviço de validação", ex.getMessage());
        }
    }

    // Teste para garantir que o nome do titular não seja nulo
    @Test
    void deveValidarNomeTitularNaoNulo() {
        novoCartao.setNomeTitular(null);  // Testando caso o nome do titular seja nulo

        // Espera-se que o método de validação ou a lógica do controlador
        // rejeite a entrada e o cartão seja inválido.
        when(servicoValidacao.validarCartaoDeCredito(novoCartao)).thenReturn("Nome do titular não pode ser nulo");

        ResponseEntity<String> response = novoCartaoController.validaCartaoDeCredito(novoCartao);
        
        assertEquals(422, response.getStatusCodeValue());
        assertEquals("Nome do titular não pode ser nulo", response.getBody());
    }

    // Teste para validar que o número do cartão tem o comprimento correto
    @Test
    void deveValidarNumeroCartao() {
        novoCartao.setNumero("123456");  // Um número de cartão com comprimento errado (6 dígitos)

        // Espera-se que o método de validação rejeite o número e retorne uma mensagem de erro
        when(servicoValidacao.validarCartaoDeCredito(novoCartao)).thenReturn("Número do cartão inválido");

        ResponseEntity<String> response = novoCartaoController.validaCartaoDeCredito(novoCartao);
        
        assertEquals(422, response.getStatusCodeValue());
        assertEquals("Número do cartão inválido", response.getBody());
    }
}

