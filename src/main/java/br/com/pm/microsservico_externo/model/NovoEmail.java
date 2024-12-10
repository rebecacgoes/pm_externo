package br.com.pm.microsservico_externo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class NovoEmail {

    @NotBlank(message = "O campo 'email' é obrigatório.")
    @Email(message = "Formato do e-mail é inválido.")
    private String email;

    @NotBlank(message = "O campo 'assunto' é obrigatório.")
    private String assunto;

    @NotBlank(message = "O campo 'mensagem' é obrigatório.")
    private String mensagem;

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
