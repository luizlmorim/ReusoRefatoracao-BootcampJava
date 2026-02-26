package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);

    void enviar(String para, String assunto, String mensagem);
}
