package br.com.pm.microsservico_externo.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class NovoCartaoDeCreditoTest {

    @Test
    public void testSetNomeTitular() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        cartao.setNomeTitular("João Silva");

        assertEquals("João Silva", cartao.getNomeTitular(), "Nome do titular deve ser 'João Silva'");
    }

    @Test
    public void testGetNomeTitular() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        cartao.setNomeTitular("Maria Souza");

        assertNotNull(cartao.getNomeTitular(), "Nome do titular não pode ser nulo");
        assertEquals("Maria Souza", cartao.getNomeTitular(), "Nome do titular deve ser 'Maria Souza'");
    }

    @Test
    public void testSetNumero() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        cartao.setNumero("1234567890123456");

        assertEquals("1234567890123456", cartao.getNumero(), "Número do cartão deve ser '1234567890123456'");
    }

    @Test
    public void testGetNumero() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        cartao.setNumero("9876543210987654");

        assertNotNull(cartao.getNumero(), "Número do cartão não pode ser nulo");
        assertEquals("9876543210987654", cartao.getNumero(), "Número do cartão deve ser '9876543210987654'");
    }

    @Test
    public void testSetValidade() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        LocalDate validade = LocalDate.of(2025, 12, 31);
        cartao.setValidade(validade);

        assertEquals(validade, cartao.getValidade(), "Data de validade deve ser '2025-12-31'");
    }

    @Test
    public void testGetValidade() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        LocalDate validade = LocalDate.of(2024, 6, 30);
        cartao.setValidade(validade);

        assertNotNull(cartao.getValidade(), "Data de validade não pode ser nula");
        assertEquals(validade, cartao.getValidade(), "Data de validade deve ser '2024-06-30'");
    }

    @Test
    public void testSetCvv() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        cartao.setCvv("123");

        assertEquals("123", cartao.getCvv(), "CVV deve ser '123'");
    }

    @Test
    public void testGetCvv() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        cartao.setCvv("456");

        assertNotNull(cartao.getCvv(), "CVV não pode ser nulo");
        assertEquals("456", cartao.getCvv(), "CVV deve ser '456'");
    }

    @Test
    public void testSetNomeTitularNull() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        cartao.setNomeTitular(null);

        assertNull(cartao.getNomeTitular(), "Nome do titular não pode ser diferente de null");
    }

    @Test
    public void testSetNumeroNull() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        cartao.setNumero(null);

        assertNull(cartao.getNumero(), "Número do cartão não pode ser diferente de null");
    }

    @Test
    public void testSetValidadeNull() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        cartao.setValidade(null);

        assertNull(cartao.getValidade(), "Data de validade não pode ser diferente de null");
    }

    @Test
    public void testSetCvvNull() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        cartao.setCvv(null);

        assertNull(cartao.getCvv(), "CVV não pode ser diferente de null");
    }

    // Teste que verifica se a instância do objeto é criada corretamente.
    @Test
    public void testCartaoDeCreditoInstance() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        
        assertNotNull(cartao, "A instância do cartão de crédito não pode ser nula");
    }

    // Teste de valores padrões quando não são definidos.
    @Test
    public void testValoresPadrao() {
        NovoCartaoDeCredito cartao = new NovoCartaoDeCredito();
        
        assertNull(cartao.getNomeTitular(), "O nome do titular deve ser nulo por padrão");
        assertNull(cartao.getNumero(), "O número do cartão deve ser nulo por padrão");
        assertNull(cartao.getValidade(), "A validade deve ser nula por padrão");
        assertNull(cartao.getCvv(), "O CVV deve ser nulo por padrão");
    }
}

