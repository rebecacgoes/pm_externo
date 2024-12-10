package br.com.pm.microsservico_externo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pm.microsservico_externo.exception.EmailNaoExisteException;
import br.com.pm.microsservico_externo.exception.FormatoEmailInvalidoException;
import br.com.pm.microsservico_externo.model.Email;
import br.com.pm.microsservico_externo.model.NovoEmail;
import br.com.pm.microsservico_externo.service.ServicoEnvioEmail;
import jakarta.validation.Valid;

@RestController
public class EmailController {

    private final ServicoEnvioEmail servicoEnvioEmail;

    public EmailController(ServicoEnvioEmail servicoEnvioEmail) {
        this.servicoEnvioEmail = servicoEnvioEmail;
    }

    @PostMapping("/enviarEmail")
    public ResponseEntity<Email> enviarEmail(@Valid @RequestBody NovoEmail novoEmail) {
        try {
            // Chama o serviço para enviar o email
            Email emailEnviado = (Email) servicoEnvioEmail.enviarEmail(novoEmail);
            return ResponseEntity.ok(emailEnviado);
        } catch (EmailNaoExisteException e) {
            // Retorna 404 se o e-mail não existir
            return ResponseEntity.status(404).body(null);
        } catch (FormatoEmailInvalidoException e) {
            // Retorna 422 se o formato do e-mail for inválido
            return ResponseEntity.unprocessableEntity().body(null);
        }
    }
}
