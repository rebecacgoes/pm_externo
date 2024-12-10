package br.com.pm.microsservico_externo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.UUID;

public class EmailTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testIdGeradoAutomaticamente() {
        // Criando um Email (herdando de NovoEmail)
        Email email = new Email();
        email.setEmail("teste@dominio.com");
        email.setAssunto("Assunto válido");
        email.setMensagem("Mensagem válida");

        // Verificando se o ID foi gerado automaticamente
        assertNotNull(email.getId());  // O ID não pode ser nulo
        assertTrue(email.getId().matches("^[a-f0-9-]{36}$"));  // Verifica se o ID é um UUID válido
    }

    @Test
    public void testEmailInvalido() {
        // Criando um NovoEmail inválido (sem e-mail)
        Email email = new Email();
        email.setEmail("");  // E-mail vazio
        email.setAssunto("Assunto válido");
        email.setMensagem("Mensagem válida");

        // Validando o objeto
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        // Imprimindo as violações para depuração
        violations.forEach(violation -> System.out.println(violation.getMessage()));

        // Verificando se há violação de validação (no caso, o e-mail é obrigatório)
        assertFalse(violations.isEmpty());  // Espera-se que haja pelo menos uma violação
    }

    @Test
    public void testEmailValido() {
        // Criando um NovoEmail válido
        Email email = new Email();
        email.setEmail("teste@dominio.com");  // Email válido
        email.setAssunto("Assunto válido");
        email.setMensagem("Mensagem válida");

        // Validando o objeto
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        // Imprimindo as violações para depuração
        violations.forEach(violation -> System.out.println(violation.getMessage()));

        // Verificando se não há violações
        assertTrue(violations.isEmpty());  // Espera-se que não haja violação
    }

    @Test
    public void testAssuntoInvalido() {
        // Criando um Email com assunto vazio
        Email email = new Email();
        email.setEmail("teste@dominio.com");
        email.setAssunto("");  // Assunto vazio
        email.setMensagem("Mensagem válida");

        // Validando o objeto
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        // Verificando se há violação de validação no campo 'assunto'
        assertFalse(violations.isEmpty());  // Espera-se que haja pelo menos uma violação
    }

    @Test
    public void testMensagemInvalida() {
        // Criando um Email com mensagem vazia
        Email email = new Email();
        email.setEmail("teste@dominio.com");
        email.setAssunto("Assunto válido");
        email.setMensagem("");  // Mensagem vazia

        // Validando o objeto
        Set<ConstraintViolation<Email>> violations = validator.validate(email);

        // Verificando se há violação de validação no campo 'mensagem'
        assertFalse(violations.isEmpty());  // Espera-se que haja pelo menos uma violação
    }

    @Test
    public void testSetAndGetId() {
        // Testando o setter e getter de 'id'
        Email email = new Email();
        String novoId = UUID.randomUUID().toString();
        email.setId(novoId);

        // Verificando se o ID foi corretamente setado
        assertEquals(novoId, email.getId());
    }
}

