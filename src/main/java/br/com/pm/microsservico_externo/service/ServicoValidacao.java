package br.com.pm.microsservico_externo.service;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.pm.microsservico_externo.model.NovoCartaoDeCredito;

@Service
public class ServicoValidacao {

    public String validarCartaoDeCredito(NovoCartaoDeCredito cartao) {

        // Valida se cartão não é nulo
        if (cartao == null) {
            return "Os dados do cartão não podem ser nulos.";
        }

        // Validação do nome do titular
        if (Objects.isNull(cartao.getNomeTitular()) || cartao.getNomeTitular().trim().isEmpty()) {
            return "Nome do titular é obrigatório.";
        }

        // Validação do número do cartão
        if (Objects.isNull(cartao.getNumero()) || cartao.getNumero().trim().isEmpty()) {
            return "Número do cartão é obrigatório.";
        }
        if (!cartao.getNumero().matches("\\d+")) {
            return "Número do cartão deve conter apenas números.";
        }
        if (!validarNumeroCartao(cartao.getNumero())) {
            return "Número do cartão inválido";
        }

        // Validação do CVV
        if (Objects.isNull(cartao.getCvv()) || cartao.getCvv().trim().isEmpty()) {
            return "CVV é obrigatório.";
        }
        if (!cartao.getCvv().matches("\\d+")) {
            return "CVV deve conter apenas números.";
        }
        if (!validarCvv(cartao.getCvv())) {
            return "CVV inválido. Deve conter 3 ou 4 dígitos";
        }

        // Validação da data de validade
        if (cartao.getValidade() == null) {
            return "Validade do cartão é obrigatória.";
        }
        if (!validarValidade(cartao.getValidade())) {
            return "Cartão expirado. Data de validade já passou";
        }

        return "Cartão válido";
    }

    private boolean validarNumeroCartao(String numero) {
        int soma = 0;
        boolean deveDobrar = false;

        // Algoritmo de Luhn para validação do número do cartão
        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numero.charAt(i));

            if (deveDobrar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }

            soma += digito;
            deveDobrar = !deveDobrar;
        }

        return soma % 10 == 0; // Se a soma for múltiplo de 10, o número do cartão é válido
    }

    private boolean validarCvv(String cvv) {
        return cvv.matches("\\d{3,4}"); // O CVV deve conter apenas 3 ou 4 dígitos
    }

    private boolean validarValidade(LocalDate validade) {
        return validade.isAfter(LocalDate.now()) || validade.isEqual(LocalDate.now()); // A data de validade deve ser hoje ou no futuro
    }
}
