package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import br.com.nogueiranogueira.aularefatoracao.solidproject.repository.GerenciadorUsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.List;

@Service
public class GerenciadorUsuarioService {

    private final GerenciadorUsuarioRepository repository;
    private final List<RegraUsuario> regras;
    private final UsuarioMailSenderService mailSender;


    public GerenciadorUsuarioService(
            GerenciadorUsuarioRepository repository,
            List<RegraUsuario> regras,
            UsuarioMailSenderService mailSender) {
        this.repository = repository;
        this.regras = regras;
        this.mailSender = mailSender;
    }

    public void criarUsuario(UsuarioDTO dto) {
        RegraUsuario regra = regras.stream()
                .filter(r -> r.getTipo().equals(dto.tipo()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo não encontrado"));

        Usuario usuario = regra.criarUsuario(dto);

        repository.salvar(usuario);

        mailSender.enviarEmailBoasVindas(usuario.getEmail());
    }

}
