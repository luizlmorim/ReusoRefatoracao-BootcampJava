package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import br.com.nogueiranogueira.aularefatoracao.solidproject.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenciadorUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(UsuarioDTO dto) {
        String tipo = dto.tipo();

        if ("COMUM".equals(tipo)) {
            // Regras para usuário comum
            validarEmail(dto.email());
            Usuario usuario = new Usuario(dto.nome(), dto.email(), dto.tipo());
            usuario.setPontos(0);
            return usuarioRepository.save(usuario);

        } else if ("VIP".equals(tipo)) {
            // Regras para usuário VIP
            validarEmail(dto.email());
            validarIdade(dto.idade());
            Usuario usuario = new Usuario(dto.nome(), dto.email(), dto.tipo());
            usuario.setPontos(100);
            return usuarioRepository.save(usuario);

        } else {
            throw new IllegalArgumentException("Tipo inválido");
        }
    }

    private void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido");
        }
    }

    private void validarIdade(int idade){
        if (idade < 18) {
            throw new IllegalArgumentException("Usuário deve ser maior de idade");
        }
    }
}
