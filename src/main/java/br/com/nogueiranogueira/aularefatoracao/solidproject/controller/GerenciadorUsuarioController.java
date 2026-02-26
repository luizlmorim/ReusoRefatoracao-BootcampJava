package br.com.nogueiranogueira.aularefatoracao.solidproject.controller;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.service.GerenciadorUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class GerenciadorUsuarioController {

    private final GerenciadorUsuarioService usuarioService;;

    public GerenciadorUsuarioController(GerenciadorUsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody UsuarioDTO dto) {
        try {
            usuarioService.criarUsuario(dto);
            return ResponseEntity.ok("Usuário crido com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
