package br.com.pm.microsservico_externo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pm.microsservico_externo.model.NovoCartaoDeCredito;
import br.com.pm.microsservico_externo.service.ServicoValidacao;

@RestController
public class NovoCartaoController {

    private final ServicoValidacao servicoValidacao;

    // Injeção via construtor
    public NovoCartaoController(ServicoValidacao servicoValidacao) {
        this.servicoValidacao = servicoValidacao;
    }

    @PostMapping("/validaCartaoDeCredito")
    public ResponseEntity<String> validaCartaoDeCredito(@RequestBody NovoCartaoDeCredito novoCartao) {
        // Chama o serviço para validar o cartão e retorna a mensagem resultante
        String resultadoValidacao = servicoValidacao.validarCartaoDeCredito(novoCartao);
        // Define o response code com base na validação
        if ("Cartão válido".equals(resultadoValidacao)) {
            // HTTP 200
            return ResponseEntity.ok(resultadoValidacao);
        } else {
            // HTTP 422 para erros de validação
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(resultadoValidacao);
        }
    }
}
