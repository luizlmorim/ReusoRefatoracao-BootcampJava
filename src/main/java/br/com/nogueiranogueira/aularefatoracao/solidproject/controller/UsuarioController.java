package br.com.nogueiranogueira.aularefatoracao.solidproject.controller;

import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import br.com.nogueiranogueira.aularefatoracao.solidproject.service.UsuarioService;
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

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    @PostMapping
    public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario) {

        usuarioService.criarUsuario(usuario);

        return ResponseEntity.ok("Usuário criado com sucesso");
    }
}
