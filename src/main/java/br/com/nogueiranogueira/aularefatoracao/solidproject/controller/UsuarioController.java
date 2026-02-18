package br.com.nogueiranogueira.aularefatoracao.solidproject.controller;

import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            return ResponseEntity.badRequest().body("E-mail inválido");
        }

        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM usuarios WHERE email = ?",
                Integer.class,
                usuario.getEmail());
        if (count != null && count > 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("E-mail já cadastrado");
        }

        jdbcTemplate.update(
                "INSERT INTO usuarios (nome, email) VALUES (?, ?)",
                usuario.getNome(),
                usuario.getEmail());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(usuario.getEmail());
        message.setSubject("Bem-vindo!");
        message.setText("Olá " + usuario.getNome() + ", seu cadastro foi realizado com sucesso.");
        if (mailSender != null) {
            mailSender.send(message);
        }

        return ResponseEntity.ok("Usuário criado com sucesso");
    }
}
