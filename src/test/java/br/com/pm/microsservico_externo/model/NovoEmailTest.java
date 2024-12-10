package br.com.pm.microsservico_externo.model;

import br.com.pm.microsservico_externo.model.NovoEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NovoEmailTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        // Inicializa o validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // Teste para garantir que o e-mail válido passa na validação
    @Test
    void deveValidarEmailCorreto() {
        NovoEmail novoEmail = new NovoEmail();
        novoEmail.setEmail("exemplo@dominio.com");
        novoEmail.setAssunto("Assunto válido");
        novoEmail.setMensagem("Mensagem válida");

        Set<ConstraintViolation<NovoEmail>> violations = validator.validate(novoEmail);

        // Não deve haver erros de validação
        assertTrue(violations.isEmpty());
    }

    // Teste para garantir que o campo 'assunto' não seja vazio
    @Test
    void deveFalharValidacaoAssuntoVazio() {
        NovoEmail novoEmail = new NovoEmail();
        novoEmail.setEmail("exemplo@dominio.com");
        novoEmail.setAssunto("");
        novoEmail.setMensagem("Mensagem válida");

        Set<ConstraintViolation<NovoEmail>> violations = validator.validate(novoEmail);

        // Deve haver um erro de validação para o campo 'assunto'
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("O campo 'assunto' é obrigatório.", violations.iterator().next().getMessage());
    }

    // Teste para garantir que o campo 'mensagem' não seja vazio
    @Test
    void deveFalharValidacaoMensagemVazia() {
        NovoEmail novoEmail = new NovoEmail();
        novoEmail.setEmail("exemplo@dominio.com");
        novoEmail.setAssunto("Assunto válido");
        novoEmail.setMensagem("");

        Set<ConstraintViolation<NovoEmail>> violations = validator.validate(novoEmail);

        // Deve haver um erro de validação para o campo 'mensagem'
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("O campo 'mensagem' é obrigatório.", violations.iterator().next().getMessage());
    }
}

