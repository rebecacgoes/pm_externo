package br.com.pm.microsservico_externo.service;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.pm.microsservico_externo.model.NovoCartaoDeCredito;

public class ServicoValidacaoTest {

    private ServicoValidacao servicoValidacao;
    private NovoCartaoDeCredito cartao;

    @BeforeEach
    public void setUp() {
        servicoValidacao = new ServicoValidacao();
        cartao = new NovoCartaoDeCredito();
    }

    @Test
    public void testValidarCartaoDeCredito_CartaoValido() {
        // Arrange
        cartao.setNomeTitular("João Silva");
        cartao.setNumero("4539578763621486"); // Número válido pelo algoritmo de Luhn
        cartao.setCvv("123");
        cartao.setValidade(LocalDate.now().plusMonths(6));

        // Act
        String resultado = servicoValidacao.validarCartaoDeCredito(cartao);

        // Assert
        assertEquals("Cartão válido", resultado);
    }

    @Test
    public void testValidarCartaoDeCredito_CartaoNulo() {
        // Act
        String resultado = servicoValidacao.validarCartaoDeCredito(null);

        // Assert
        assertEquals("Os dados do cartão não podem ser nulos.", resultado);
    }

    @Test
    public void testValidarCartaoDeCredito_NomeTitularNulo() {
        // Arrange
        cartao.setNomeTitular(null);
        cartao.setNumero("4539578763621486");
        cartao.setCvv("123");
        cartao.setValidade(LocalDate.now().plusMonths(6));

        // Act
        String resultado = servicoValidacao.validarCartaoDeCredito(cartao);

        // Assert
        assertEquals("Nome do titular é obrigatório.", resultado);
    }

    @Test
    public void testValidarCartaoDeCredito_NumeroCartaoInvalido() {
        // Arrange
        cartao.setNomeTitular("João Silva");
        cartao.setNumero("1234567890123456"); // Número inválido pelo algoritmo de Luhn
        cartao.setCvv("123");
        cartao.setValidade(LocalDate.now().plusMonths(6));

        // Act
        String resultado = servicoValidacao.validarCartaoDeCredito(cartao);

        // Assert
        assertEquals("Número do cartão inválido", resultado);
    }

    @Test
    public void testValidarCartaoDeCredito_NumeroCartaoNaoNumerico() {
        // Arrange
        cartao.setNomeTitular("João Silva");
        cartao.setNumero("1234abcd5678efgh");
        cartao.setCvv("123");
        cartao.setValidade(LocalDate.now().plusMonths(6));

        // Act
        String resultado = servicoValidacao.validarCartaoDeCredito(cartao);

        // Assert
        assertEquals("Número do cartão deve conter apenas números.", resultado);
    }

    @Test
    public void testValidarCartaoDeCredito_CvvInvalido() {
        // Arrange
        cartao.setNomeTitular("João Silva");
        cartao.setNumero("4539578763621486");
        cartao.setCvv("12"); // CVV inválido (menos de 3 dígitos)
        cartao.setValidade(LocalDate.now().plusMonths(6));

        // Act
        String resultado = servicoValidacao.validarCartaoDeCredito(cartao);

        // Assert
        assertEquals("CVV inválido. Deve conter 3 ou 4 dígitos", resultado);
    }

    @Test
    public void testValidarCartaoDeCredito_CvvNaoNumerico() {
        // Arrange
        cartao.setNomeTitular("João Silva");
        cartao.setNumero("4539578763621486");
        cartao.setCvv("12A");
        cartao.setValidade(LocalDate.now().plusMonths(6));

        // Act
        String resultado = servicoValidacao.validarCartaoDeCredito(cartao);

        // Assert
        assertEquals("CVV deve conter apenas números.", resultado);
    }

    @Test
    public void testValidarCartaoDeCredito_ValidadeExpirada() {
        // Arrange
        cartao.setNomeTitular("João Silva");
        cartao.setNumero("4539578763621486");
        cartao.setCvv("123");
        cartao.setValidade(LocalDate.now().minusDays(1));

        // Act
        String resultado = servicoValidacao.validarCartaoDeCredito(cartao);

        // Assert
        assertEquals("Cartão expirado. Data de validade já passou", resultado);
    }

    @Test
    public void testValidarCartaoDeCredito_ValidadeNula() {
        // Arrange
        cartao.setNomeTitular("João Silva");
        cartao.setNumero("4539578763621486");
        cartao.setCvv("123");
        cartao.setValidade(null);

        // Act
        String resultado = servicoValidacao.validarCartaoDeCredito(cartao);

        // Assert
        assertEquals("Validade do cartão é obrigatória.", resultado);
    }
}

