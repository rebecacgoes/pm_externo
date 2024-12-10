package br.com.pm.microsservico_externo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;

import br.com.pm.microsservico_externo.exception.EmailNaoExisteException;
import br.com.pm.microsservico_externo.exception.FormatoEmailInvalidoException;
import br.com.pm.microsservico_externo.model.Email;
import br.com.pm.microsservico_externo.model.NovoEmail;

@Service
public class ServicoEnvioEmail {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.from.email}")
    private String fromEmail;

    // Método de envio de e-mail utilizando SendGrid
    public Email enviarEmail(NovoEmail novoEmail) throws EmailNaoExisteException, FormatoEmailInvalidoException {
        // Validações
        if (novoEmail.getEmail() == null || novoEmail.getEmail().isEmpty()) {
            throw new EmailNaoExisteException("E-mail não fornecido.");
        }

        if (!isValidEmail(novoEmail.getEmail())) {
            throw new FormatoEmailInvalidoException("Formato de e-mail inválido.");
        }

        // Criando o cliente SendGrid
        com.sendgrid.helpers.mail.objects.Email from = new com.sendgrid.helpers.mail.objects.Email(fromEmail);
        com.sendgrid.helpers.mail.objects.Email to = new com.sendgrid.helpers.mail.objects.Email(novoEmail.getEmail());
        String subject = novoEmail.getAssunto();
        Content content = new Content("text/plain", novoEmail.getMensagem());
        Mail mail = new Mail(from, subject, to, content);

        // Enviar o e-mail usando o SendGrid API
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            // Configura para rodar em modo sandbox para testes
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            // Envia a requisição
            com.sendgrid.Response response = sg.api(request);
            System.out.println("Response Status: " + response.getStatusCode());

            // Criando o objeto Email com o ID gerado
            Email emailEnviado = new Email();
            emailEnviado.setEmail(novoEmail.getEmail());
            emailEnviado.setAssunto(novoEmail.getAssunto());
            emailEnviado.setMensagem(novoEmail.getMensagem());

            return emailEnviado;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar o e-mail: " + e.getMessage(), e);
        }
    }

    // Valida o formato do e-mail utilizando expressão regular
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
}

