package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import br.com.nogueiranogueira.aularefatoracao.solidproject.repository.GerenciadorUsuarioRepository;
import br.com.nogueiranogueira.aularefatoracao.solidproject.service.RegraUsuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenciadorUsuarioService {

    private final GerenciadorUsuarioRepository repository;

    private final List<RegraUsuario> regras;

    public GerenciadorUsuarioService(
            GerenciadorUsuarioRepository repository,
            List<RegraUsuario> regras
    ) {

        this.repository = repository;
        this.regras = regras;

    }

    public Usuario criarUsuario(UsuarioDTO dto){

        RegraUsuario regra =
                regras.stream()
                        .filter(r -> r.getTipo().equals(dto.tipo()))
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException("Tipo não encontrado"));

        Usuario usuario = regra.criarUsuario(dto);

        return repository.salvar(usuario);

    }

}
