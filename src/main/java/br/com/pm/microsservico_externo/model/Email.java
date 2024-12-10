package br.com.pm.microsservico_externo.model;

import java.util.UUID;

public class Email extends NovoEmail {

    private String id = UUID.randomUUID().toString();

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
