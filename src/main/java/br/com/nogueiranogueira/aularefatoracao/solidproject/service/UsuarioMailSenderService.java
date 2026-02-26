package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import br.com.nogueiranogueira.aularefatoracao.solidproject.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMailSenderService {

    private final EmailService emailService;

    public UsuarioMailSenderService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void enviarEmailBoasVindas(String email) {
        emailService.enviar(email, "Bem-vindo", "Obrigado por se registar!");
    }

}
