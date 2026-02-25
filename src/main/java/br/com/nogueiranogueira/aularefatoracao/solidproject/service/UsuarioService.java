package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final JdbcTemplate jdbcTemplate;
    private final JavaMailSender mailSender;

    public UsuarioService(
            JdbcTemplate jdbcTemplate,
            JavaMailSender mailSender
    ){
        this.jdbcTemplate = jdbcTemplate;
        this.mailSender = mailSender;
    }

    public void criarUsuario(Usuario usuario){

        validarUsuario(usuario);

        verificarEmailDuplicado(usuario);

        salvarUsuario(usuario);

        enviarEmail(usuario);

    }

    private void validarUsuario(Usuario usuario){

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty())
            throw new RuntimeException("Nome é obrigatório");

        if (usuario.getEmail() == null || !usuario.getEmail().contains("@"))
            throw new RuntimeException("Email inválido");

    }

    private void verificarEmailDuplicado(Usuario usuario){

        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM usuarios WHERE email = ?",
                Integer.class,
                usuario.getEmail()
        );

        if(count != null && count > 0)
            throw new RuntimeException("Email já cadastrado");

    }

    private void salvarUsuario(Usuario usuario){

        jdbcTemplate.update(
                "INSERT INTO usuarios (nome, email) VALUES (?, ?)",
                usuario.getNome(),
                usuario.getEmail()
        );

    }

    private void enviarEmail(Usuario usuario){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(usuario.getEmail());
        message.setSubject("Bem-vindo");
        message.setText("Olá " + usuario.getNome());

        mailSender.send(message);

    }

}