package br.com.pm.microsservico_externo.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CartaoDeCreditoTest {

    @Test
    void testCartaoDeCreditoComId() {
        // Criação de um novo CartaoDeCredito
        CartaoDeCredito cartao = new CartaoDeCredito();
        cartao.setNomeTitular("João da Silva");
        cartao.setNumero("1234567812345678");
        cartao.setValidade(LocalDate.of(2025, 12, 12));
        cartao.setCvv("123");
        cartao.setId(1);

        // Verifica se o campo id foi corretamente setado e o objeto foi criado com sucesso
        assertEquals(1, cartao.getId());
        assertEquals("João da Silva", cartao.getNomeTitular());
        assertEquals("1234567812345678", cartao.getNumero());
        assertEquals(LocalDate.of(2025, 12, 12), cartao.getValidade());
        assertEquals("123", cartao.getCvv());
    }

    @Test
    void testAlterarIdCartao() {
        // Criação de um novo CartaoDeCredito
        CartaoDeCredito cartao = new CartaoDeCredito();
        cartao.setNomeTitular("Maria Oliveira");
        cartao.setNumero("8765432187654321");
        cartao.setValidade(LocalDate.of(2024, 11, 10));
        cartao.setCvv("321");
        cartao.setId(1);
        
        // Altera o id para outro valor
        cartao.setId(2);
        
        // Verifica se o id foi atualizado corretamente
        assertEquals(2, cartao.getId());
    }
}

