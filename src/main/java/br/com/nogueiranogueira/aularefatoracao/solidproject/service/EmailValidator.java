package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {

    public void validar(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
}